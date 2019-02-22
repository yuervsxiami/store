package com.cnuip.base.domain.console;

import com.cnuip.base.base.BaseModel;
import com.cnuip.base.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Data
@ApiModel(value = "省份表", description = "省份表")
public class Province extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 名称 */
    @ApiModelProperty(value="名称", name="name", dataType="String", example="江苏")
    private String name;


    @Override
    public String checkValue(){

        if (StringUtils.isNullOrEmpty(this.getName())){
            return "PROVINCE_NAME_CANNOT_NULL";
        }
        if (this.getName() == null){
            return "PROVINCE_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getName()) > 255){
            return "PROVINCE_NAME_MAX_LENGTH_ERROR";
        }

        return null;
    }

    @Override
    public Province setDefaultValue(){


        return this;
    }
}
