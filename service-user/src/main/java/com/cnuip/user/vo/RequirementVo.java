package com.cnuip.user.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhaozhihui
 * Date: 2018/10/12.
 * Time: 14:44
 */

@Data
public class RequirementVo {
    private EnterpriseRequire enterpriseRequire;
    private List<RequirementProfessor> professors;
}
