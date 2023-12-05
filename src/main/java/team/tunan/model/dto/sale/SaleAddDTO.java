package team.tunan.model.dto.sale;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/11/30
 */
@Data
public class SaleAddDTO {

    private Long productId;

    private Long userId;

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
