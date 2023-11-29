package team.tunan.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import team.tunan.model.dto.product.ProductQueryRequest;
import team.tunan.model.entity.Product;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tunan
 * @since 2023-11-29
 */
public interface IProductService extends IService<Product> {

    Wrapper<Product> getQueryWrapper(ProductQueryRequest productQueryRequest);
}
