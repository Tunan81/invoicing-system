package team.tunan.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.tunan.common.HttpCodeEnum;
import team.tunan.constant.UserConstant;
import team.tunan.exception.BusinessException;
import team.tunan.mapper.UserMapper;
import team.tunan.model.entity.User;
import team.tunan.service.UserService;

import javax.servlet.http.HttpServletRequest;


/**
 * 用户服务实现
 *
 * @author <a href="https://github.com/Tunan81">图南</a>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{
    /**
     * 获取当前登录用户
     *
     */
    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(HttpCodeEnum.NOT_LOGIN_ERROR);
        }
        // 从数据库查询（追求性能的话可以注释，直接走缓存）
        long userId = currentUser.getId();
        currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new BusinessException(HttpCodeEnum.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }
}
