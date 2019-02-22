package com.cnuip.patent.service;


import com.cnuip.base.domain.params.FavoriteParam;
import com.cnuip.base.domain.patent.Favorite;
import com.cnuip.base.service.AbstractService;

/**
 * Created by Crixalis on 2018/5/4.
 */
public interface FavoriteService extends AbstractService<Favorite, FavoriteParam> {

    /**
     * 加入收藏
     * @param favorite
     * @return
     */
    Favorite addFavorite(Favorite favorite) throws Exception;
}
