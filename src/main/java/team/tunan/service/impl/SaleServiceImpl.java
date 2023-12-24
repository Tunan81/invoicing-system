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
        // 1。根据商品名称查询商品是否存在
        Product product = productService.getOne(new QueryWrapper<Product>()
                .eq("product_name", saleAddDTO.getProductName()));
        if (product == null) {
            return Result.fail("商品不存在");
        }
        if (saleAddDTO.getSaleQuantity() > product.getStockQuantity()) {
            return Result.fail("库存不足");
        }
        Sale sale = new Sale();
        sale.setSaleQuantity(saleAddDTO.getSaleQuantity());
        sale.setSaleDate(saleAddDTO.getSaleDate());
        sale.setProductId(product.getProductId());
        sale.setUserId(saleAddDTO.getUserId());
        sale.setSaleTotal(saleAddDTO.getSaleTotal());
        // 2。减少库存
        product.setStockQuantity(product.getStockQuantity() - saleAddDTO.getSaleQuantity());
        // 3。保存销售记录
        saleMapper.insert(sale);
        // 4。更新商品库存
        productService.updateById(product);
        return Result.success("更新成功");
    }

    @Override
    public Result<?> myRemoveById(Long saleId) {
        Sale sale = saleMapper.selectById(saleId);
        if (sale == null) {
            return Result.fail("销售记录不存在");
        }
        Product product = productService.getById(sale.getProductId());
        product.setStockQuantity(product.getStockQuantity() + sale.getSaleQuantity());
        productService.updateById(product);
        saleMapper.deleteById(saleId);
        return Result.success("删除成功");
    }

    /**
     * 修改
     * @param sale 实体
     * @return Result
     */
    @Override
    public Result<?> myUpdateById(Sale sale) {
        Sale sale1 = saleMapper.selectById(sale.getSaleId());
        if (sale1 == null) {
            return Result.fail("销售记录不存在");
        }
        Product product = productService.getById(sale1.getProductId());
        product.setStockQuantity(product.getStockQuantity() + sale1.getSaleQuantity() - sale.getSaleQuantity());
        productService.updateById(product);
        saleMapper.updateById(sale);
        return Result.success("更新成功");
    }
}
