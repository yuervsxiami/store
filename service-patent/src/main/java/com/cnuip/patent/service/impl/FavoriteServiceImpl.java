package com.cnuip.patent.service.impl;

import com.cnuip.base.domain.params.FavoriteParam;
import com.cnuip.base.domain.patent.Favorite;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.patent.exception.FavoriteException;
import com.cnuip.patent.exception.enums.ResultEnum;
import com.cnuip.patent.repository.base.FavoriteBaseMapper;
import com.cnuip.patent.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * Created by Crixalis on 2018/5/4.
 */

@Service
public class FavoriteServiceImpl extends AbstractServiceImpl<Favorite, FavoriteParam> implements FavoriteService {

    @Autowired
    private FavoriteBaseMapper favoriteBaseMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Favorite addFavorite(Favorite favorite) throws Exception{
        //PatentTypeEnum patentType = getByStringTypeName(PatentTypeEnum.class,"getName",favorite.getPatentType());
       // favorite.setPatentType(patentType.name());
        // 查询专利是否被收藏
        FavoriteParam favoriteParam = new FavoriteParam();
        favoriteParam.setUserId(favorite.getUserId());
        favoriteParam.setAn(favorite.getAn());
        Favorite favorite1 = favoriteBaseMapper.selectOne(favoriteParam);
        if(favorite1 != null){
            throw new FavoriteException(ResultEnum.FAVORITE_EXIST);
        }
        favoriteBaseMapper.insert(favorite);
        return favorite;
    }

    private static <T extends Enum<T>> T getByStringTypeName(Class<T> clazz,String getTypeNameMethodName, String typeName){
        T result = null;
        try{
            T[] arr = clazz.getEnumConstants();
            Method targetMethod = clazz.getDeclaredMethod(getTypeNameMethodName);
            String typeNameVal = null;
            for(T entity:arr){
                typeNameVal = targetMethod.invoke(entity).toString();
                if(typeNameVal.contentEquals(typeName)){
                    result = entity;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
