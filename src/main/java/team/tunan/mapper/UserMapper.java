package team.tunan.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import team.tunan.model.entity.User;

/**
 * 用户 映射层。
 *
 * @author TuNan
 * @since 2023-11-21
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
