package team.tunan.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import team.tunan.common.PageRequest;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProviderQueryRequest extends PageRequest implements Serializable {

    private Long providerId;

    private String providerName;

    private static final long serialVersionUID = 1L;
}
