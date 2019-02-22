package com.cnuip.console.repository;

import com.cnuip.base.domain.console.Splash;
import com.cnuip.base.domain.params.SplashParam;
import com.cnuip.console.vo.SplashVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SplashMapper {
    List<Splash> findValidSplash(@Param("endTime") Date endTime);
    Page<SplashVo> selectPagedSplash(@Param("params") SplashParam params, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
    Splash currentSplash(@Param("currentTime") Date currentTime);
}
