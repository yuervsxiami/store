package com.cnuip.patent.repository.base;

import com.cnuip.base.domain.params.FavoriteParam;
import com.cnuip.base.domain.patent.Favorite;
import com.cnuip.base.repository.AbstractMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Repository
public interface FavoriteBaseMapper extends AbstractMapper<Favorite, FavoriteParam>
{}