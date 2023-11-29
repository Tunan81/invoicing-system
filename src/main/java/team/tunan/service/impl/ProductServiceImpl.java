package team.tunan.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import team.tunan.mapper.ProductMapper;
import team.tunan.model.dto.product.ProductQueryRequest;
import team.tunan.model.entity.Product;
import team.tunan.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tunan
 * @since 2023-11-29
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Override
    public Wrapper<Product> getQueryWrapper(ProductQueryRequest productQueryRequest) {
        if (productQueryRequest == null) {
            return null;
        }
        Long id = productQueryRequest.getId();
        String productName = productQueryRequest.getProductName();
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.like(productName != null, "product_name", productName);
        return queryWrapper;
    }
}
