package team.tunan.model.dto.purchase;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/11/30
 */
@Data
public class PurchaseAddDTO {

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

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
