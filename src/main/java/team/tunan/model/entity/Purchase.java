package team.tunan.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类。
 *
 * @author TuNan
 * @since 2023-11-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase implements Serializable {

    private Long purchaseId;

    private Long productId;

    private Long userId;

    private Date purchaseTime;

    private Integer purchaseQuantity;

    private BigDecimal totalCost;

    private Date createTime;

    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

}
