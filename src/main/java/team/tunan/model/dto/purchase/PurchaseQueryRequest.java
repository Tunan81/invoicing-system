package team.tunan.model.dto.purchase;

import lombok.Data;
import lombok.EqualsAndHashCode;
import team.tunan.common.PageRequest;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/11/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PurchaseQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private Long userId;

    private Date purchaseTime;

    private static final long serialVersionUID = 1L;
}
