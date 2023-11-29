package team.tunan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import team.tunan.annotation.AuthCheck;
import team.tunan.common.DeleteRequest;
import team.tunan.common.HttpCodeEnum;
import team.tunan.common.Result;
import team.tunan.constant.UserConstant;
import team.tunan.exception.BusinessException;
import team.tunan.exception.ThrowUtils;
import team.tunan.model.dto.user.*;
import team.tunan.model.entity.User;
import team.tunan.model.vo.LoginUserVO;
import team.tunan.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @author Tunan
 * @version 1.0
 * @date 2023/11/28
 */
@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "TuNan";

    @Resource
    private UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return Result.fail(HttpCodeEnum.PARAMS_ERROR);
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return Result.success(result);
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
        return Result.success(loginUserVO);
    }

    /**
     * 用户登出
     */
    @PostMapping("/logout")
    public Result<Boolean> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        boolean result = userService.userLogout(request);
        return Result.success(result);
    }

    /**
     * 获取当前登录用户
     */
    @GetMapping("/get/login")
    public Result<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        return Result.success(userService.getLoginUserVO(user));
    }

    /**
     * 创建用户
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Long> addUser(@RequestBody UserAddRequest userAddRequest) {
        if (userAddRequest == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userAddRequest, user);
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + 123456).getBytes());
        user.setUserPassword(encryptPassword);
        boolean result = userService.save(user);
        ThrowUtils.throwIf(!result, HttpCodeEnum.OPERATION_ERROR);
        return Result.success(user.getId());
    }

    /**
     * 删除用户
     */
    @PostMapping("/delete")
    //@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest) {
        System.out.println(deleteRequest);
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        boolean b = userService.removeById(deleteRequest.getId());
        return Result.success(b);
    }

    /**
     * 更新用户
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        if (userUpdateRequest == null || userUpdateRequest.getId() == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, HttpCodeEnum.OPERATION_ERROR);
        return Result.success(true);
    }

    /**
     * 分页获取用户列表（仅管理员）
     */
    @PostMapping("/list/page")
    //@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Page<User>> listUserByPage(@RequestBody UserQueryRequest userQueryRequest) {
        long current = userQueryRequest.getCurrent();
        long size = userQueryRequest.getPageSize();
        Page<User> userPage = userService.page(new Page<>(current, size),
                userService.getQueryWrapper(userQueryRequest));
        return Result.success(userPage);
    }

    /**
     * 更新个人信息
     *
     */
    @PostMapping("/update/my")
    public Result<Boolean> updateMyUser(@RequestBody UserUpdateMyRequest userUpdateMyRequest,
                                        HttpServletRequest request) {
        if (userUpdateMyRequest == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        User user = new User();
        BeanUtils.copyProperties(userUpdateMyRequest, user);
        user.setId(loginUser.getId());
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, HttpCodeEnum.OPERATION_ERROR);
        return Result.success(true);
    }



}
