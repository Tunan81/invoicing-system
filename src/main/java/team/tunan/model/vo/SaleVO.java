package team.tunan.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/11/30
 */
@Data
public class SaleVO {
    private Long saleId;

    private Long userId;

    private String userName;

    private Long productId;

    private String productName;

    /**
     * 销售时间
     */
    private Date saleDate;

    /**
     * 销售数量
     */
    private Integer saleQuantity;

    /**
     * 销售总价
     */
    private BigDecimal saleTotal;
}
