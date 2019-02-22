package com.cnuip.user.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RequirementProfessor {
    private long id;
    private long requirementId;
    private long originId;
    private String professorRemoteKey;
    private String professorName;
    private String professorCollegeName;
    private float score;
    private float matchingDegree;
    private String keywords;
    private Date createTime;
    private Date updateTime;
}
