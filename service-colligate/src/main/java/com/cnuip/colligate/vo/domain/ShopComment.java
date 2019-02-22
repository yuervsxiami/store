package com.cnuip.colligate.vo.domain;

import com.cnuip.base.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ShopComment extends BaseModel {
    private Long id;
    private Long shopId;
    private String reviewContent;
    private String replyContent;
    private String commentSource;
    private String state;
    private Long reviewUserId;
    private String reviewUsername;
    private Date createdTime;
    private Date updatedTime;
}
