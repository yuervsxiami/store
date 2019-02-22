package com.cnuip.user.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.cnuip.base.domain.console.Team;
import com.cnuip.base.domain.enums.UserActionEnum;
import com.cnuip.base.domain.enums.YesNoEnum;
import com.cnuip.base.domain.params.*;
import com.cnuip.base.domain.user.*;
import com.cnuip.base.service.impl.AbstractServiceImpl;
import com.cnuip.base.utils.StringUtils;
import com.cnuip.base.utils.TreeUtils;
import com.cnuip.base.web.BaseException;
import com.cnuip.base.web.enums.ResponseBaseEnum;
import com.cnuip.user.repository.UserMapper;
import com.cnuip.user.repository.VerifyCodeMapper;
import com.cnuip.user.repository.base.*;
import com.cnuip.user.rest.exceptions.CustomException;
import com.cnuip.user.rest.exceptions.ResponseEnum;
import com.cnuip.user.service.AliSmsService;
import com.cnuip.user.service.UserService;
import com.cnuip.user.vo.*;
import com.cnuip.user.vo.domain.Shop;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: 王志斌
 * @Date: 2018/4/3 15:27
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends AbstractServiceImpl<User, UserParam> implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserBaseMapper userBaseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleBaseMapper userRoleBaseMapper;

    @Autowired
    private RoleBaseMapper roleBaseMapper;

    @Autowired
    private RoleAuthorityBaseMapper roleAuthorityBaseMapper;

    @Autowired
    private AuthorityBaseMapper authorityBaseMapper;

    @Autowired
    private VerifyCodeBaseMapper verifyCodeBaseMapper;

    @Autowired
    private AliSmsService aliSmsService;

    @Autowired
    private LoginLogBaseMapper loginLogBaseMapper;

    @Autowired
    private UserTeamBaseMapper userTeamBaseMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private VerifyCodeMapper verifyCodeMapper;

    @Value("${remote.cnuip.url}")
    private String cnuipUrl;


    @Override
    public UserVo login(UserVo user, String platform, String ip) throws Exception {
        UserParam userParam = new UserParam();
        User one;
        if (LoginTypeEnum.PWD.equals(user.getLoginTypeEnum())) {
            /**
             * 判断用户名和密码
             */
            if (platform.equals("web")) {
                userParam.setOrganizationId(user.getOrganizationId());
            }
            userParam.setUsername(user.getUsername());
            userParam.setIsDelete(YesNoEnum.NO.toString());
            one = userBaseMapper.selectOne(userParam);
            if (one == null) {
                throw new CustomException(ResponseEnum.USER_ISNOTEXIST);
            } else if (!passwordEncoder.matches(user.getPassword(), one.getPassword())) {
                throw new CustomException(ResponseEnum.USER_LOGIN_PASSWORD_ERROR);
            }
        } else if (LoginTypeEnum.MSG.equals(user.getLoginTypeEnum())) {
            /**
             * 判断手机号和验证码
             */
            userParam.setIsDelete(YesNoEnum.NO.toString());
            userParam.setUsername(user.getUsername());
            one = userBaseMapper.selectOne(userParam);
            if (one == null) {
                throw new CustomException(ResponseEnum.PHONE_ISNOTEXIST);
            } else {
                if (StringUtils.isNullOrEmpty(user.getVerifyCode())) {
                    throw new CustomException(ResponseEnum.VERIFY_CODE_NULL);
                }
                VerifyCodeParam verifyCodeParams = new VerifyCodeParam();
                verifyCodeParams.setCode(user.getVerifyCode());
                verifyCodeParams.setUsername(user.getUsername());
                verifyCodeParams.setAction(UserActionEnum.LOGIN.toString());
                VerifyCode verifyCode = verifyCodeBaseMapper.selectOne(verifyCodeParams);
                if (verifyCode == null) {
                    throw new CustomException(ResponseEnum.VERIFY_CODE_ERROR);
                }

                if (!verifyCodeMapper.compareTime(verifyCode.getCreatedTime())) {
                    throw new CustomException(ResponseEnum.VERIFY_CODE_OUTTIME);
                }
            }

        } else {
            throw new CustomException(ResponseEnum.USER_LOGIN_STATE_ERROR);
        }
        UserVo userVo = this.queryDetail(one);
        if (userVo.getRoles().isEmpty()) {
            throw new CustomException(ResponseEnum.USER_LOGIN_AUTH_ERROR);
        }

        if (!platform.equals("web")) {
            for (Role role : userVo.getRoles()) {
                if (role.getName().equals("ROLE_ADMIN")) {
                    throw new CustomException(ResponseEnum.USER_APP_LOGIN_ERROR);
                }
            }
        }

        return userVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User addUser(UserVo userVo) throws Exception {
        userVo.setUsername(userVo.getPhone());
        //检查用户名唯一性
        User user = userBaseMapper.selectOneByField("username", userVo.getUsername(), null);
        userVo.setPassword(passwordEncoder.encode(userVo.getPassword()));
        if (user != null) {
            throw new CustomException(ResponseEnum.PHONE_ISEXIST);
        }
        //插入user表基本信息
        this.check(userVo);
        User insert = this.insert(userVo);
        //插入用户项目组信息
        List<Team> teams = userVo.getTeams();
        if (teams != null && !teams.isEmpty()) {
            for (Team e : teams) {
                UserTeam userTeam = new UserTeam();
                userTeam.setUserId(userVo.getId());
                userTeam.setTeamId(e.getId());
                userTeam.setTeamName(e.getName());
                userTeamBaseMapper.insert(userTeam);
            }
        }
        //插入用户角色信息
        List<Role> roles = userVo.getRoles();
        if (roles != null && !roles.isEmpty()) {
            for (Role e : roles) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userVo.getId());
                userRole.setRoleId(e.getId());
                userRoleBaseMapper.insert(userRole);
            }
        }

        // 调用cnuip2接口添加专利书包店铺
        if (!("admin".equals(userVo.getUsername()))) {
            addShop(userVo);
        }

        return insert;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deleteUserVo(Long userId) throws Exception {
        User delete = this.updateFieldByKey(userId, "isDelete", YesNoEnum.YES.toString());
        if (delete != null) {
            //删除用户和角色对应关系
            UserRoleParam userRoleParam = new UserRoleParam();
            userRoleParam.setUserId(userId);
            userRoleBaseMapper.delete(userRoleParam);
            //删除用户和项目组对应关系
            UserTeamParam userTeamParam = new UserTeamParam();
            userTeamParam.setUserId(userId);
            userTeamBaseMapper.delete(userTeamParam);
        }
        return "success";
    }

    @Override
    public UserVo queryDetail(User one) {
        /**
         * 获取角色
         */
        UserRoleParam userRoleParam = new UserRoleParam();
        userRoleParam.setUserId(one.getId());
        List<UserRole> userRoleList = userRoleBaseMapper.getAll(userRoleParam);
        List<Role> roles = new ArrayList<>();
        for (UserRole e : userRoleList) {
            Role role = roleBaseMapper.selectOneByKey(e.getRoleId());
            if (role != null && YesNoEnum.NO.toString().equals(role.getIsDelete()))
                roles.add(role);
        }

        /**
         * 获取权限
         */
        List<AuthorityVo> authorities = new ArrayList<>();
        List<Long> authoritiesId = new ArrayList<>();
        for (Role e : roles) {
            RoleAuthorityParam roleAuthorityParam = new RoleAuthorityParam();
            roleAuthorityParam.setRoleId(e.getId());
            List<RoleAuthority> all = roleAuthorityBaseMapper.getAll(roleAuthorityParam);
            if (all != null) {
                for (RoleAuthority element : all) {
                    if (!authoritiesId.contains(element.getAuthorityId())) {
                        authoritiesId.add(element.getAuthorityId());
                    }
                }
            }
        }
        if (!authoritiesId.isEmpty()) {
            for (Long authorityId : authoritiesId) {
                Authority authority = authorityBaseMapper.selectOneByKey(authorityId);
                if (authority != null) {
                    AuthorityVo authorityVo = new AuthorityVo();
                    BeanUtils.copyProperties(authority, authorityVo);
                    authorities.add(authorityVo);
                }
            }
        }

        /**
         * 获取项目组
         */
        UserTeamParam userTeamParam = new UserTeamParam();
        userTeamParam.setUserId(one.getId());
        List<UserTeam> userTeamList = userTeamBaseMapper.getAll(userTeamParam);
        List<Team> teamList = new ArrayList<>();
        for (UserTeam userTeam : userTeamList) {
            Team team = new Team();
            team.setId(userTeam.getTeamId());
            team.setName(userTeam.getTeamName());
            teamList.add(team);
        }

        List<AuthorityVo> authorityList = TreeUtils.listToTree(authorities);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(one, userVo);
        userVo.setRoles(roles);
        userVo.setAuthorities(authorityList);
        userVo.setTeams(teamList);
        return userVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVo register(UserVo userVo, String ip) throws Exception {
        /**
         * 用户名唯一(前端注册用户 用户名就是手机号)
         */
        if (userVo.getUsername() != null) {
            userVo.setPhone(userVo.getUsername());
        } else {
            userVo.setUsername(userVo.getPhone());
        }
        UserParam userParam = new UserParam();
        userParam.setUsername(userVo.getUsername());
        int count = userBaseMapper.count(userParam);
        if (count > 0) {
            throw new CustomException(ResponseEnum.PHONE_ISEXIST);
        }

        /**
         * 校验验证码
         */
        if (StringUtils.isNullOrEmpty(userVo.getVerifyCode())) {
            throw new CustomException(ResponseEnum.VERIFY_CODE_ERROR);
        }
        VerifyCodeParam verifyCodeParams = new VerifyCodeParam();
        verifyCodeParams.setCode(userVo.getVerifyCode());
        verifyCodeParams.setUsername(userVo.getUsername());
        verifyCodeParams.setAction(VerifyCodeActionEnum.REGISTER.toString());
        VerifyCode verifyCode = verifyCodeBaseMapper.selectOne(verifyCodeParams);
        if (verifyCode == null) {
            throw new CustomException(ResponseEnum.VERIFY_CODE_ERROR);
        }

        Date createdTime = verifyCode.getCreatedTime();
        Date currentTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createdTime);
        calendar.add(Calendar.MINUTE, 1);
        if (currentTime.after(calendar.getTime())) {
            throw new CustomException(ResponseEnum.VERIFY_CODE_ERROR);
        }

        userVo.setIsDelete(YesNoEnum.NO.name());
        userVo.setPassword(passwordEncoder.encode(userVo.getPassword()));
        //查询操作人
        userParam.setUsername("admin");
        userParam.setOrganizationId(userVo.getOrganizationId());
        User user = userBaseMapper.selectOne(userParam);
        if (user != null) {
            userVo.setEditorId(user.getId());
            userVo.setEditorName("admin");
        }
        User one = this.insert(userVo);

        /**
         * 插入用户角色信息
         */
        RoleParam roleParam = new RoleParam();
        if (userVo.getOrganizationId() == null) {
            throw new BaseException(ResponseBaseEnum.USER_ORGANIZATION_ID_CANNOT_NULL);
        }
        roleParam.setOrganizationId(userVo.getOrganizationId());
        roleParam.setName("ROLE_NORMAL");
        Role role = roleBaseMapper.selectOne(roleParam);
        UserRole userRole = new UserRole();
        userRole.setUserId(userVo.getId());
        if (role != null) {
            userRole.setRoleId(role.getId());
            userRoleBaseMapper.insert(userRole);
        }

        /**
         * 登录日志
         */
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(userVo.getId());
        loginLog.setIp(ip);
        loginLog.setUsername(userVo.getUsername());
        loginLogBaseMapper.insert(loginLog);

        // 调用cnuip2接口添加专利书包店铺
        addShop(userVo);

        /**
         * 发短信
         */
//        String json = "{\"username\":\"%1s\"}";
//        try {
//            aliSmsService.sendSms(userVo.getUsername(), "SMS_130924784", json, userVo.getUsername());
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
        return this.queryDetail(one);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer resetPassword(UserValidateVo userValidateVo) throws Exception {
        /**
         * 手机号是否存在
         */
        UserParam userParam = new UserParam();
        userParam.setUsername(userValidateVo.getPhone());
        User user = userBaseMapper.selectOne(userParam);
        if (user == null) {
            throw new CustomException(ResponseEnum.PHONE_ISNOTEXIST);
        }

        /**
         * 校验验证码是否存在
         */
        if (StringUtils.isNullOrEmpty(userValidateVo.getCode())) {
            throw new CustomException(ResponseEnum.VERIFY_CODE_ERROR);
        }
        VerifyCodeParam verifyCodeParam = new VerifyCodeParam();
        verifyCodeParam.setCode(userValidateVo.getCode());
        verifyCodeParam.setUsername(userValidateVo.getPhone());
        verifyCodeParam.setAction(VerifyCodeActionEnum.FORGET.toString());
        VerifyCode verifyCode = verifyCodeBaseMapper.selectOne(verifyCodeParam);
        if (verifyCode == null) {
            throw new CustomException(ResponseEnum.VERIFY_CODE_ERROR);
        }

        /**
         * 校验验证码是否失效
         */
        if (!verifyCodeMapper.compareTime(verifyCode.getCreatedTime())) {
            throw new CustomException(ResponseEnum.VERIFY_CODE_OUTTIME);
        }
//        Date createdTime = verifyCode.getCreatedTime();
//        Date currentTime = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(createdTime);
//        calendar.add(Calendar.MINUTE, 1);
//        if (currentTime.after(calendar.getTime())) {
//            throw new CustomException(ResponseEnum.VERIFY_CODE_ERROR);
//        }
        return userBaseMapper.updateFieldByKey(user.getId(), "password", passwordEncoder.encode(userValidateVo.getPassword()));
    }

    @Override
    public PageInfo<User> queryDepartmentNone(Long orgId, Integer pageNum, Integer pageSize) {

        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<User> page = (Page<User>) userMapper.queryDepartmentNone(orgId, pageNum, pageSize);
        return page.toPageInfo();
    }

    @Override
    public List<Long> queryDeptIdBase(Long orgId, Long powersId) {
        return userMapper.queryDeptIdBase(orgId, powersId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User updateUser(Long userId,User user) throws Exception {
        // 查询用户权限

//        if(!(StringUtils.isNullOrEmpty(user.getPassword()))){
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//        }
        try{
            Shop shop = new Shop();
            shop.setRemoteKey(userId.toString());
            shop.setShopTypeId(3l);
            shop.setSiteId(1l);
            shop.setImageUrl(user.getImageUrl());
            shop.setPa(user.getOrganizationName());
            shop.setName(user.getRealName());
            shop.setRealName(user.getRealName());
            shop.setPhone(user.getPhone());
            shop.setIdCardNo(user.getIdCardNo());
            shop.setStartTime(new Date());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            shop.setEndTime(simpleDateFormat.parse("2049-12-31 00:00:00"));
            shop.setState("EXAMINING");
            shop.setIntroduction(user.getIntroduction());
            shop.setHonor(user.getHonor());
            shop.setDirection(user.getDirection());
            shop.setTitle(user.getTitle());
            ResponseEntity<Map> responseEntity = restTemplate.exchange(cnuipUrl + "cnuip2-mservice-shop/v1/shop/byremotekey", HttpMethod.PUT, new HttpEntity<>(shop), Map.class);
        }catch (Exception e){
            throw new ClientException("调用cnuip店铺修改失败");
        }
        userBaseMapper.updateByKey(user.getId(),user);
        return userBaseMapper.selectOneByKey(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer pwdChange(String userId, String passwordOld, String passwordNew) throws Exception {
        User user = userBaseMapper.selectOneByKey(Long.valueOf(userId));
        if (user == null) {
            throw new CustomException(ResponseEnum.USER_ISNOTEXIST);
        } else if (!passwordEncoder.matches(passwordOld, user.getPassword())) {
            throw new CustomException(ResponseEnum.USER_LOGIN_PASSWORD_ERROR);
        }
        return userBaseMapper.updateFieldByKey(Long.valueOf(userId), "password", passwordEncoder.encode(passwordNew));
    }

    private void addShop(UserVo userVo) throws Exception {
        Shop shop = new Shop();
        shop.setRemoteKey(userVo.getId().toString());
        shop.setShopTypeId(3l);
        shop.setSiteId(1l);
        shop.setImageUrl(userVo.getImageUrl());
        shop.setPa(userVo.getOrganizationName());
        shop.setName(userVo.getRealName());
        shop.setRealName(userVo.getRealName());
        shop.setPhone(userVo.getPhone());
        shop.setIdCardNo(userVo.getIdCardNo());
        shop.setStartTime(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        shop.setEndTime(simpleDateFormat.parse("2049-12-31 00:00:00"));
        shop.setState("EXAMINING");
        shop.setIntroduction(userVo.getIntroduction());
        shop.setHonor(userVo.getHonor());
        shop.setDirection(userVo.getDirection());
        shop.setTitle(userVo.getTitle());
        LinkedHashMap shopMap;
        try {
            ResponseEntity<Map> responseEntity = restTemplate.exchange(cnuipUrl + "cnuip2-mservice-shop/v1/shop", HttpMethod.POST, new HttpEntity<>(shop), Map.class);
            shopMap = (LinkedHashMap) responseEntity.getBody().get("result");
            if(!responseEntity.getBody().get("code").toString().equals("0")){
                if(responseEntity.getBody().get("code").toString().equals("2001")){
                    throw new CustomException(ResponseEnum.SHOP_ENTER_USERNAME_ERROR);
                }
                logger.error("ADD USER-SHOP ERRER++++++++++++++++++++++++++++");
                throw new CustomException(ResponseEnum.CNUIP_CONNECT_ERROR);
            }
        } catch (Exception e) {
            if (e instanceof CustomException) {
                throw e;
            }
            logger.error("ADD USER-SHOP ERRER:" + e);
            throw new CustomException(ResponseEnum.CNUIP_CONNECT_ERROR);
        }
    }
}