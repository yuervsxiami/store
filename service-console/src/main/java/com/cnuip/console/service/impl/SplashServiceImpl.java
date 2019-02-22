package com.cnuip.console.service.impl;

import com.cnuip.base.domain.console.Splash;
import com.cnuip.base.domain.params.SplashParam;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.console.jedis.JedisClient;
import com.cnuip.console.jedis.JedisClientPool;
import com.cnuip.console.repository.SplashMapper;
import com.cnuip.console.repository.base.SplashBaseMapper;
import com.cnuip.console.rest.exceptions.CustomException;
import com.cnuip.console.service.SplashService;
import com.cnuip.console.vo.SplashVo;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SplashServiceImpl extends AbstractServiceImpl<Splash, SplashParam> implements SplashService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SplashBaseMapper splashBaseMapper;
    @Autowired
    private SplashMapper splashMapper;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public Splash insertSplash(Splash splash) throws Exception {
        splash.setId(null);
        checkSplash(splash);
        splashBaseMapper.insert(splash);
        return splashBaseMapper.selectOneByKey(splash.getId());
    }

    @Override
    public Splash updateSplash(Splash splash) throws CustomException {
        if (splash.getShowStartTime() != null || splash.getShowEndTime() != null)
            checkSplash(splash);
        splashBaseMapper.updateByKey(splash.getId(), splash);
        return splashBaseMapper.selectOneByKey(splash.getId());
    }

    @Override
    public PageInfo<SplashVo> list(SplashParam splashParam) {
        if (splashParam.getPageNum() == null) {
            splashParam.setPageNum(1);
        }
        if (splashParam.getPageSize() == null) {
            splashParam.setPageSize(10);
        }

        splashParam.setOrderBy("orderseq");

        return splashMapper.selectPagedSplash(splashParam, splashParam.getPageNum(), splashParam.getPageSize()).toPageInfo();
    }

    @Override
    public Splash current() {
        logger.info("-----------从缓存找splashCurrent缓存----------");
        Splash splash = jedisClient.get("store2.console.splashCurrent",Splash.class);
        if(splash!=null){
            return splash;
        }
        logger.info("-----------从数据库中找splashCurrent缓存------");
        splash =  splashMapper.currentSplash(Calendar.getInstance().getTime());
        if(splash!=null){
            //计算缓存过期时间
            long expireSecond = (splash.getShowEndTime().getTime() - Calendar.getInstance().getTime().getTime())/1000;
            logger.info("-----------缓存过期时间"+expireSecond+"--------");
            jedisClient.del("store2.console.splashCurrent");
            jedisClient.setExpire("store2.console.splashCurrent",splash,expireSecond);
        }else{
            logger.info("-----------从缓存清除splashCurrent缓存----------");
            jedisClient.del("store2.console.splashCurrent");
        }
        return splash;

    }

    private void checkSplash(Splash splash) throws CustomException {
        Date startTime = splash.getShowStartTime();
        Date endTime = splash.getShowEndTime();
        if (startTime.compareTo(endTime) >= 0)
            throw new CustomException("开始时间大于结束时间");
        if (endTime.compareTo(Calendar.getInstance().getTime()) <= 0)
            throw new CustomException("结束时间小于当前时间");
        List<Splash> splashes = splashMapper.findValidSplash(Calendar.getInstance().getTime());
        for (Splash sp : splashes) {
            if (sp.getId().equals(splash.getId()))
                continue;
            Date showEndTime = sp.getShowEndTime();
            Date showStartTime = sp.getShowStartTime();
            if (startTime.after(showStartTime) && startTime.before(showEndTime))
                throw new CustomException("显示周期有重叠");
            if (endTime.after(showStartTime) && endTime.before(showEndTime))
                throw new CustomException("显示周期有重叠");
            if (startTime.before(showStartTime) && endTime.after(showEndTime))
                throw new CustomException("显示周期有重叠");
        }
    }


}
