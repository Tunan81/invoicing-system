package team.tunan.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import team.tunan.model.dto.user.UserQueryRequest;
import team.tunan.model.entity.User;
import team.tunan.model.vo.LoginUserVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author <a href="https://gitee.com/xia-haike">图南</a>
 */
public interface UserService extends IService<User> {

    /**
     * 获取当前登录用户
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户登出
     *
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取脱敏的已登录用户信息
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 获取查询条件
     *
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

}
