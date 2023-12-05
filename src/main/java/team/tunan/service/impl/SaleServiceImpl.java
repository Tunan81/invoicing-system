package team.tunan.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import team.tunan.common.Result;
import team.tunan.mapper.SaleMapper;
import team.tunan.model.dto.sale.SaleAddDTO;
import team.tunan.model.dto.sale.SaleQueryRequest;
import team.tunan.model.entity.Product;
import team.tunan.model.entity.Sale;
import team.tunan.service.IProductService;
import team.tunan.service.ISaleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.tunan.service.UserService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tunan
 * @since 2023-11-29
 */
@Service
public class SaleServiceImpl extends ServiceImpl<SaleMapper, Sale> implements ISaleService {

    @Resource
    private SaleMapper saleMapper;

    @Resource
    private UserService userService;

    @Resource
    private IProductService productService;

    @Override
    public Wrapper<Sale> getQueryWrapper(SaleQueryRequest saleQueryRequest) {
        if (saleQueryRequest == null) {
            return null;
        }
        Long saleId = saleQueryRequest.getSaleId();
        Long userId = saleQueryRequest.getUserId();
        Long productId = saleQueryRequest.getProductId();
        Date saleTime = saleQueryRequest.getSaleTime();
        QueryWrapper<Sale> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(saleId != null, "sale_id", saleId);
        queryWrapper.eq(userId != null, "user_id", userId);
        queryWrapper.eq(productId != null, "product_id", productId);
        queryWrapper.eq(saleTime != null, "sale_time", saleTime);
        return queryWrapper;
    }

    @Override
    public Result<?> saveDate(SaleAddDTO saleAddDTO) {
        // 1。根据商品id查询商品库存是否足够
        Product product = productService.getById(saleAddDTO.getProductId());
        if (saleAddDTO.getSaleQuantity() > product.getStockQuantity()) {
            return Result.fail("库存不足");
        }
        Sale sale = new Sale();
        sale.setSaleQuantity(saleAddDTO.getSaleQuantity());
        sale.setSaleDate(saleAddDTO.getSaleDate());
        sale.setProductId(saleAddDTO.getProductId());
        sale.setUserId(saleAddDTO.getUserId());
        // 2。减少库存
        product.setStockQuantity(product.getStockQuantity() - saleAddDTO.getSaleQuantity());
        // 3。保存销售记录
        saleMapper.insert(sale);
        // 4。更新商品库存
        productService.updateById(product);
        return Result.success("更新成功");
    }
}
