package team.tunan.service;


import com.baomidou.mybatisplus.extension.service.IService;
import team.tunan.model.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author <a href="https://github.com/Tunan81">图南</a>
 */
public interface UserService extends IService<User> {

    /**
     * 获取当前登录用户
     */
    User getLoginUser(HttpServletRequest request);

}
