package com.cnuip.colligate.service.impl;

import com.cnuip.colligate.exception.ClientException;
import com.cnuip.colligate.service.ArticleService;
import com.cnuip.colligate.vo.ArticleQueryParam;
import com.cnuip.gaea.service.web.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @Author: 王志斌
 * @Date: 2018/4/3 15:27
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${remote.cnuip.url}")
    private String cnuipUrl;

    @Override
    public ApiResponse list(ArticleQueryParam articleQueryParam) throws Exception {
        if (articleQueryParam.getOrderBy() == null || articleQueryParam.getOrderBy().isEmpty()) {
            articleQueryParam.setOrderBy("a.created_time desc");
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(cnuipUrl+"cnuip2-mservice-article/v1/article/");
        Field[] declaredFields = ArticleQueryParam.class.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object o = field.get(articleQueryParam);
            if (o != null) {
                if(o instanceof Date){
                    o = ((Date)o).getTime();
                }
                builder.queryParam(field.getName(), o);
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Request-SiteId","1");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<ApiResponse> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, ApiResponse.class);
        if (((ResponseEntity<ApiResponse>) response).getStatusCodeValue() != 200) {
            throw new ClientException("调用CNUIP服务失败!");
        } else {
            return response.getBody();
        }

    }
}
