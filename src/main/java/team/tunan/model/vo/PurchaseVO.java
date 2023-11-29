package team.tunan.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/11/29
 */
@Data
public class PurchaseVO {
    private Long id;

    private Long userId;

    private String userName;

    private Long productId;

    private String productName;

    /**
     * 采购时间
     */
    private Date purchaseTime;

    /**
     * 采购数量
     */
    private Integer purchaseQuantity;

    /**
     * 采购总价
     */
    private BigDecimal totalCost;

}
