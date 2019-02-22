package com.cnuip.base.base;

import com.cnuip.base.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: 王志斌
 * @Date: 2018/7/12 09:53
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryParam {
    /**
     * like 方式 只针对 String 类型 (默认是NONE)
     */
    private LikeModeEnum likeMode;

    /**
     * 使用 AND 还是 OR (默认 false)
     */
    private Boolean useOr;

    /**
     * 排序
     */
    private String orderBy;

    /**
     * 分页参数
     */
    private Integer pageNum;
    private Integer pageSize;

    public void setOrderBy(String orderBy) {
        this.orderBy = StringUtils.camelToUnderline(orderBy);
    }

    public QueryParam() {
        this.likeMode = LikeModeEnum.NONE;
        this.useOr = false;
        this.pageNum = 1;
        this.pageSize = 10;
    }
}
