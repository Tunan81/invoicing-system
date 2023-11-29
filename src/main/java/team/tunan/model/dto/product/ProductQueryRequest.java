package team.tunan.model.dto.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import team.tunan.common.PageRequest;

import java.io.Serializable;

/**
 * @author Tunan
 * @version 1.0
 * @date 2023/11/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private String productName;

    private static final long serialVersionUID = 1L;
}
