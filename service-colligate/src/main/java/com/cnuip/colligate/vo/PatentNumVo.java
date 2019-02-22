package com.cnuip.colligate.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by xjt on 2018/8/22.
 */
@Getter
@Setter
@ApiModel(value = "专利使用统计数据VO", description = "专利使用统计数据VO")
public class PatentNumVo {

    @ApiModelProperty(value="有效数", name="validNum", dataType="Long")
    private Long validNum;

    @ApiModelProperty(value="已委托", name="usedNum", dataType="Long")
    private Long usedNum;

    @ApiModelProperty(value="可委托", name="usableNum", dataType="Long")
    private Long usableNum;
}
