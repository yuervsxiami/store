package com.cnuip.process.vo;

import com.cnuip.base.domain.process.Process;
import com.cnuip.base.domain.process.ProcessAttachment;
import com.cnuip.base.domain.process.ProcessPerson;
import com.cnuip.base.domain.process.ProcessRequisition;
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
@ApiModel(value = "提案VO", description = "提案Vo,包含提案附件", parent = Process.class)
public class ProcessVo extends Process {

    @ApiModelProperty(value="提案附件", name="processAttachmentList", dataType="ArrayList")
    private List<ProcessAttachment> processAttachmentList;

    @ApiModelProperty(value="提案申请书", name="processRequisitionList", dataType="ArrayList")
    private List<ProcessRequisition> processRequisitionList;

    @ApiModelProperty(value="提案环节", name="processTaskVo", dataType="ArrayList")
    private List<ProcessTaskVo> processTaskVoList;

    @ApiModelProperty(value="提案抄送人集合", name="personList", dataType="Long")
    private List<ProcessPerson> personList;
}
