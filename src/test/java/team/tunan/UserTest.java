package team.tunan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team.tunan.config.SpringConfig;
import team.tunan.service.UserService;

import javax.annotation.Resource;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/11/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class UserTest {
    @Resource
    private UserService userService;

    @Test
    public void test() {
        System.out.println(userService.getById(78728958133092352L));
    }

    @Test
    public void testList() {
        System.out.println(userService.list());
    }
}
