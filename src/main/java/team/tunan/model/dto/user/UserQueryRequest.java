package team.tunan.model.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import team.tunan.common.PageRequest;


import java.io.Serializable;

/**
 * 用户查询请求
 *
 * @author <a href="https://github.com/Tunan81">图南</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}