package com.cnuip.process.vo;

import com.cnuip.base.domain.process.TmplProcess;
import com.cnuip.base.domain.process.TmplProcessPerson;
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
@ApiModel(value = "流程模板VO", description = "流程模板VO,包含流程环节", parent = TmplProcess.class)
public class TmplProcessVo extends TmplProcess{
    /** 流程环节模板集合 */
    @ApiModelProperty(value="流程环节模板集合", name="tmplProcessTaskList", dataType="ArrayList")
    private List<TmplProcessTaskVo> tmplProcessTaskList;

    @ApiModelProperty(value="流程模板抄送人集合", name="userList", dataType="Long")
    private List<TmplProcessPerson> tmplProcessPersonList;
}
