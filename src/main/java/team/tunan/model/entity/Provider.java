package team.tunan.model.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * 这个表与本项目无关，是实验十二的作业
 */

@Data
public class Provider {

    private Long providerId;

    private String providerNo;

    private String providerName;

    private String ProviderPhone;

    private String ProviderPerson;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic(value = "0",delval = "1")
    private Integer isDelete;

}
