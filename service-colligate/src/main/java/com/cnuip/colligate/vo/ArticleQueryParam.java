package com.cnuip.colligate.vo;

import com.cnuip.base.base.QueryParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleQueryParam extends QueryParam{
    private String label;
    private Date startTime;
    private Date endTime;
    private String userName;
    private Integer pageSize;
    private Integer pageNum;
    private Long articleTypeId;
    private String state;
    private Long siteId;
    private Integer no;
    private Long id;
    private String keywords;
    private String orderBy;
}
