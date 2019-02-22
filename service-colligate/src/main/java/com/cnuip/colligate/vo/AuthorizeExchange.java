package com.cnuip.colligate.vo;

import com.cnuip.base.base.BaseModel;
import com.cnuip.base.domain.authorize.AuthorizePatent;
import lombok.Data;

import java.util.List;

/**
 * Created by Crixalis on 2018/4/14.
 */

@Data
public class AuthorizeExchange extends BaseModel {
    /**
     * 数据同步key
     */
    private String remoteKey;

    private String remoteId;

    /**
     * 专利列表
     */
    private List<AuthorizePatent> authorizePatents;
}
