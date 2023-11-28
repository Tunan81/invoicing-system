package team.tunan.common;


import lombok.Data;
import team.tunan.constant.CommonConstant;

/**
 * 分页请求
 *
 * @author <a href="https://github.com/Tunan81">图南</a>
 */
@Data
public class PageRequest {


    /**
     * 当前页号
     */
    private long pageNumber = 1;

    /**
     * 页面大小
     */
    private long PageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;
}
