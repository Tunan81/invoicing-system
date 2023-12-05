package team.tunan.model.dto.sale;

import lombok.Data;
import lombok.EqualsAndHashCode;
import team.tunan.common.PageRequest;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/11/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SaleQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long saleId;

    private Long userId;

    private Long productId;

    private Date saleTime;

    private static final long serialVersionUID = 1L;
}
