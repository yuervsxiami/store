package com.cnuip.colligate.vo;

import com.cnuip.base.domain.authorize.Authorize;
import com.cnuip.base.domain.authorize.AuthorizePatent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by xjt on 2018/8/22.
 */
@Getter
@Setter
@ApiModel(value = "专利委托VO", description = "专利委托VO,包含委托合同和专利", parent = Authorize.class)
public class AuthorizeVo extends Authorize {
    //委托合同相关专利
    @ApiModelProperty(value="专利列表", name="authorizePatentList", dataType="ArrayList")
    private List<AuthorizePatent> authorizePatentList;
    @ApiModelProperty(value="委托人", name="realName", dataType="String")
    private String realName;
}
