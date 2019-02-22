package com.cnuip.colligate.service.impl;

import com.cnuip.base.domain.console.Department;
import com.cnuip.base.domain.console.Post;
import com.cnuip.base.domain.console.Powers;
import com.cnuip.base.domain.enums.YesNoEnum;
import com.cnuip.base.domain.params.UserParam;
import com.cnuip.base.domain.user.User;
import com.cnuip.base.utils.ClientServiceUtils;
import com.cnuip.colligate.client.ConsoleClient;
import com.cnuip.colligate.client.UserClient;
import com.cnuip.colligate.exception.ClientException;
import com.cnuip.colligate.service.ConsoleService;
import com.cnuip.gaea.service.web.ApiResponse;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsoleServiceImpl implements ConsoleService {
    @Autowired
    private UserClient userClient;

    @Autowired
    private ConsoleClient consoleClient;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public List<Department> getTreeExamine(Long organizationId, Long powersId) throws Exception {
//        ApiResponse<List<DepartmentVo>> listApiResponse = consoleClient.searchTree(organizationId);
//        if (listApiResponse.getCode() == 0) {
//            List<DepartmentVo> result = listApiResponse.getResult();
//            return this.deleteNoManDept(result);
//        }else {
//            logger.error(listApiResponse.getError() + "," + listApiResponse.getMessage() + "," + listApiResponse.getDetailMessage());
//            throw new ClientException(ClientEnum.SERVICE_ERROR);
//        }
        ApiResponse<List<Long>> listApiResponse = new ClientServiceUtils<List<Long>, UserClient>().exec(userClient, "queryDeptIdBase", organizationId, powersId);
        //ApiResponse<List<Long>> listApiResponse = userClient.queryDeptIdBase(organizationId, powersId);
        if (listApiResponse.getCode() == 0) {
            List<Long> deptIds = listApiResponse.getResult();
            if (deptIds.isEmpty()) {
                return new ArrayList<>();
            } else {
                ApiResponse<List<Department>> departmentList = new ClientServiceUtils<List<Department>, ConsoleClient>().exec(consoleClient, "baseListExamine", deptIds);
                //ApiResponse<List<Department>> departmentList = consoleClient.baseListExamine(deptIds);
                if (departmentList.getCode() == 0) {
                    return departmentList.getResult();
                } else {
                    throw new Exception("【调用consoleClient错误】" + listApiResponse.getDetailMessage());
                }
            }
        } else {
            throw new Exception("【调用userClient错误】" + listApiResponse.getDetailMessage());
        }
    }

    @Override
    public Department deleteDept(Long id) throws Exception {
        UserParam userParam = new UserParam();
        userParam.setIsDelete(YesNoEnum.NO.toString());
        userParam.setDepartmentId(id);
        ApiResponse<PageInfo<User>> pageInfoApiResponse = new ClientServiceUtils<PageInfo<User>, UserClient>().exec(userClient, "queryUser", userParam);
        //ApiResponse<PageInfo<User>> pageInfoApiResponse = userClient.queryUser(userParam);
        if (pageInfoApiResponse.getCode() != 0) {
            throw new ClientException(pageInfoApiResponse.getCode(),pageInfoApiResponse.getMessage());
        } else {
            List<User> list = pageInfoApiResponse.getResult().getList();
            if (!list.isEmpty()) {
                throw new ClientException("无法删除!部门内存在用户");
            } else {
                ApiResponse<Department> delete = new ClientServiceUtils<Department, ConsoleClient>().exec(consoleClient, "delete", id);
                //ApiResponse<Department> delete = consoleClient.delete(id);
                if (delete.getCode() != 0) {
                    throw new ClientException(delete.getCode(),delete.getMessage());
                } else {
                    return delete.getResult();
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Powers deletePowers(Long id) throws Exception {
        UserParam userParam = new UserParam();
        userParam.setIsDelete(YesNoEnum.NO.toString());
        userParam.setPowersId(id);
        ApiResponse<PageInfo<User>> pageInfoApiResponse = new ClientServiceUtils<PageInfo<User>, UserClient>().exec(userClient, "queryUser", userParam);
        //ApiResponse<PageInfo<User>> pageInfoApiResponse = userClient.queryUser(userParam);
        if (pageInfoApiResponse.getCode() != 0) {
            throw new ClientException(pageInfoApiResponse.getCode(),pageInfoApiResponse.getMessage());
        } else {
            List<User> list = pageInfoApiResponse.getResult().getList();
            if (!list.isEmpty()) {
                throw new ClientException("无法删除!该职权下存在用户");
            } else {
                ApiResponse<Powers> powersApiResponse = new ClientServiceUtils<Powers, ConsoleClient>().exec(consoleClient, "deletePowers", id);
                //ApiResponse<Powers> powersApiResponse = consoleClient.deletePowers(id);
                if (powersApiResponse.getCode() != 0) {
                    throw new ClientException(powersApiResponse.getCode(),powersApiResponse.getMessage());
                } else {
                    return powersApiResponse.getResult();
                }
            }
        }
    }

    @Override
    public Post deletePost(Long id) throws Exception {
        UserParam userParam = new UserParam();
        userParam.setIsDelete(YesNoEnum.NO.toString());
        userParam.setPostId(id);
        ApiResponse<PageInfo<User>> pageInfoApiResponse = new ClientServiceUtils<PageInfo<User>, UserClient>().exec(userClient, "queryUser", userParam);
        //ApiResponse<PageInfo<User>> pageInfoApiResponse = userClient.queryUser(userParam);
        if (pageInfoApiResponse.getCode() != 0) {
            throw new ClientException(pageInfoApiResponse.getCode(),pageInfoApiResponse.getMessage());
        } else {
            List<User> list = pageInfoApiResponse.getResult().getList();
            if (!list.isEmpty()) {
                throw new ClientException("无法删除!该岗位下存在用户");
            } else {
                ApiResponse<Post> postApiResponse = new ClientServiceUtils<Post, ConsoleClient>().exec(consoleClient, "deletePost", id);
                // ApiResponse<Post> postApiResponse = consoleClient.deletePost(id);
                if (postApiResponse.getCode() != 0) {
                    throw new ClientException(postApiResponse.getCode(),postApiResponse.getMessage());
                } else {
                    return postApiResponse.getResult();
                }
            }
        }
    }

//    private List<DepartmentVo> deleteNoManDept(List<DepartmentVo> result) throws Exception {
//        Iterator<DepartmentVo> iterator = result.iterator();
//        while (iterator.hasNext()) {
//            DepartmentVo vo = iterator.next();
//            if (vo.getChildren() != null) {
//                List<DepartmentVo> departmentVos = this.deleteNoManDept(vo.getChildren());
//                UserParam userParam = new UserParam();
//                userParam.setDepartmentId(vo.getId());
//                userParam.setIsDelete(YesNoEnum.NO.toString());
//                ApiResponse<PageInfo<User>> pageInfoApiResponse = userClient.queryUser(userParam);
//                if (pageInfoApiResponse.getCode() == 0) {
//                    PageInfo<User> result1 = pageInfoApiResponse.getResult();
//                    if (result1.getList().isEmpty() && departmentVos.isEmpty()) {
//                        iterator.remove();
//                    }
//                } else {
//                    logger.error(pageInfoApiResponse.getError() + "," + pageInfoApiResponse.getMessage() + "," + pageInfoApiResponse.getDetailMessage());
//                    throw new ClientException(ClientEnum.SERVICE_ERROR);
//                }
//
//            } else {
//                UserParam userParam = new UserParam();
//                userParam.setDepartmentId(vo.getId());
//                userParam.setIsDelete(YesNoEnum.NO.toString());
//                ApiResponse<PageInfo<User>> pageInfoApiResponse = userClient.queryUser(userParam);
//                if (pageInfoApiResponse.getCode() == 0) {
//                    PageInfo<User> result1 = pageInfoApiResponse.getResult();
//                    if (result1.getList().isEmpty()) {
//                        iterator.remove();
//                    }
//                } else {
//                    logger.error(pageInfoApiResponse.getError() + "," + pageInfoApiResponse.getMessage() + "," + pageInfoApiResponse.getDetailMessage());
//                    throw new ClientException(ClientEnum.SERVICE_ERROR);
//                }
//            }
//        }
//        return result;
//    }
}
