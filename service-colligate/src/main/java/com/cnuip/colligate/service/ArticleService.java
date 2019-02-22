package com.cnuip.colligate.service;

import com.cnuip.colligate.vo.ArticleQueryParam;
import com.cnuip.gaea.service.web.ApiResponse;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface ArticleService {

    ApiResponse list(ArticleQueryParam articleQueryParam) throws Exception;

}
