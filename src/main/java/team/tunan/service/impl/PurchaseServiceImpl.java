package team.tunan.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.tunan.common.HttpCodeEnum;
import team.tunan.common.Result;
import team.tunan.mapper.PurchaseMapper;
import team.tunan.model.dto.purchase.PurchaseAddDTO;
import team.tunan.model.dto.purchase.PurchaseQueryRequest;
import team.tunan.model.entity.Product;
import team.tunan.model.entity.Purchase;
import team.tunan.service.IProductService;
import team.tunan.service.IPurchaseService;
import team.tunan.service.UserService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Tunan
 * @since 2023-11-29
 */
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements IPurchaseService {

    @Resource
    private PurchaseMapper purchaseMapper;

    @Resource
    private IProductService productService;

    @Override
    public Wrapper<Purchase> getQueryWrapper(PurchaseQueryRequest purchaseQueryRequest) {
        if (purchaseQueryRequest == null) {
            return null;
        }
        Long id = purchaseQueryRequest.getPurchaseId();
        Long userId = purchaseQueryRequest.getUserId();
        Date purchaseTime = purchaseQueryRequest.getPurchaseTime();
        QueryWrapper<Purchase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(userId != null, "user_id", userId);
        queryWrapper.eq(purchaseTime != null, "purchase_time", purchaseTime);
        return queryWrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveDate(PurchaseAddDTO purchaseAddDTO) {
        // 1.根据商品名称查询商品ID
        Product product = productService.getOne(new QueryWrapper<Product>().eq("product_name", purchaseAddDTO.getProductName()));
        if (product == null) {
            // 2.如果商品不存在，添加商品
            product = new Product();
            product.setProductName(purchaseAddDTO.getProductName());
            product.setPurchasePrice(purchaseAddDTO.getTotalCost());
            product.setStockQuantity(purchaseAddDTO.getPurchaseQuantity());
            productService.save(product);
        } else {
            // 如果商品存在，更新商品库存和采购价格
            product.setPurchasePrice(purchaseAddDTO.getTotalCost());
            product.setStockQuantity(product.getStockQuantity() + purchaseAddDTO.getPurchaseQuantity());
            productService.updateById(product);
        }
        // 3.添加采购记录
        Purchase purchase = new Purchase();
        purchase.setProductId(product.getProductId());
        purchase.setUserId(purchaseAddDTO.getUserId());
        purchase.setPurchaseTime(purchaseAddDTO.getPurchaseTime());
        purchase.setPurchaseQuantity(purchaseAddDTO.getPurchaseQuantity());
        purchase.setTotalCost(purchaseAddDTO.getTotalCost());
        return purchaseMapper.insert(purchase) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> myRemoveById(Long purchaseId) {
        Purchase purchase = purchaseMapper.selectById(purchaseId);
        if (purchase == null) {
            throw new RuntimeException("采购记录不存在");
        }
        Product product = productService.getById(purchase.getProductId());
        if (product == null) {
            throw new RuntimeException("商品不存在");
        }
        purchaseMapper.deleteById(purchaseId);
        product.setStockQuantity(product.getStockQuantity() - purchase.getPurchaseQuantity());
        productService.updateById(product);
        return Result.success("删除成功");
    }

    @Override
    public HttpCodeEnum myUpdate(PurchaseAddDTO purchaseAddDTO) {
        Purchase purchase = purchaseMapper.selectById(purchaseAddDTO.getPurchaseId());
        if (purchase == null) {
            return HttpCodeEnum.PARAMS_ERROR;
        }
        Product product = productService.getById(purchase.getProductId());
        if (product == null) {
            return HttpCodeEnum.PARAMS_ERROR;
        }
        // 1.更新商品库存
        product.setStockQuantity(product.getStockQuantity() - purchase.getPurchaseQuantity() + purchaseAddDTO.getPurchaseQuantity());
        product.setPurchasePrice(purchaseAddDTO.getTotalCost());
        productService.updateById(product);
        // 2.更新采购记录
        purchase.setPurchaseQuantity(purchaseAddDTO.getPurchaseQuantity());
        purchase.setTotalCost(purchaseAddDTO.getTotalCost());
        purchase.setPurchaseTime(purchaseAddDTO.getPurchaseTime());
        purchaseMapper.updateById(purchase);
        return HttpCodeEnum.SUCCESS;
    }
}
