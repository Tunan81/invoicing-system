package team.tunan.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
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

    @TableId
    private Long saleId;

    private Long productId;

    private Long userId;

    private Date saleDate;

    private String saleQuantity;

    private BigDecimal saleTotal;

    private Date createTime;

    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic(value = "0",delval = "1")
    private Integer isDelete;
}
