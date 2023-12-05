package team.tunan.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import team.tunan.mapper.PurchaseMapper;
import team.tunan.model.dto.purchase.PurchaseAddDTO;
import team.tunan.model.dto.purchase.PurchaseQueryRequest;
import team.tunan.model.entity.Product;
import team.tunan.model.entity.Purchase;
import team.tunan.model.vo.PurchaseVO;
import team.tunan.service.IProductService;
import team.tunan.service.IPurchaseService;

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
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements IPurchaseService {

    @Resource
    private PurchaseMapper purchaseMapper;

    @Resource
    private IProductService productService;

    @Resource
    private UserService userService;

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
        }
        else {
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
}
