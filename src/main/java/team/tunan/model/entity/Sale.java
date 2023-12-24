package team.tunan.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
@TableName("sale")
@NoArgsConstructor
@AllArgsConstructor
public class Sale implements Serializable {

    /**
     * 销售ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long saleId;

    /**
     * 产品ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
