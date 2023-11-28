package team.tunan.service.impl;


import team.tunan.mapper.ProductMapper;
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

}
