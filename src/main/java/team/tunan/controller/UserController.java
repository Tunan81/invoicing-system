package team.tunan.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.tunan.common.Result;
import team.tunan.service.UserService;

import javax.annotation.Resource;


/**
 * @author Tunan
 * @version 1.0
 * @date 2023/11/28
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 根据id查询用户
     */
    @GetMapping("/get")
    public Result<?> getUserById(Long id) {
        return Result.success(userService.list());
    }
}
