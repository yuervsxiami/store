package com.cnuip.user.service;

import com.cnuip.base.domain.params.UserParam;
import com.cnuip.base.domain.user.User;
import com.cnuip.base.service.AbstractService;
import com.cnuip.user.vo.UserValidateVo;
import com.cnuip.user.vo.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface UserService extends AbstractService<User, UserParam> {
    UserVo login(UserVo user, String platform, String ip) throws Exception;

    User addUser(UserVo userVo) throws Exception;

    String deleteUserVo(Long userId) throws Exception;

    UserVo queryDetail(User one) ;

    UserVo register(UserVo check, String ip) throws Exception;

    Integer resetPassword(UserValidateVo userValidateVo) throws Exception;

    PageInfo<User> queryDepartmentNone(Long orgId, Integer pageNum, Integer pageSize);

    List<Long> queryDeptIdBase(Long orgId, Long powersId);

    User updateUser(Long userId, User user) throws Exception;

    Integer pwdChange(String userId, String passwordOld, String passwordNew) throws Exception;
}
