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
public class Product implements Serializable {

    private Long productId;

    private String productName;

    private String productDescription;

    private BigDecimal purchasePrice;

    private BigDecimal salePrice;

    private Integer stockQuantity;

    private Date createTime;

    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

}
