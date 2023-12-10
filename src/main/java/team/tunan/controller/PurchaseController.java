package team.tunan.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import team.tunan.common.HttpCodeEnum;
import team.tunan.common.Result;
import team.tunan.exception.BusinessException;
import team.tunan.exception.ThrowUtils;
import team.tunan.model.dto.purchase.PurchaseAddDTO;
import team.tunan.model.dto.purchase.PurchaseQueryRequest;
import team.tunan.model.entity.Purchase;
import team.tunan.model.vo.PurchaseVO;
import team.tunan.service.IProductService;
import team.tunan.service.IPurchaseService;
import team.tunan.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Tunan
 * @since 2023-11-29
 */
@Slf4j
@RestController
@RequestMapping("/purchase")
@CrossOrigin(allowCredentials = "true")
public class PurchaseController {

    @Resource
    private IPurchaseService purchaseService;

    @Resource
    private IProductService productService;

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody PurchaseAddDTO purchaseAddDTO) {
        if (purchaseAddDTO == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        return Result.success(purchaseService.saveDate(purchaseAddDTO));
    }

    /**
     * 删除
     *
     * @param purchaseId 主键
     * @return Result
     */
    @PostMapping("/delete")
    public Result<?> delete(Long purchaseId) {
        if (purchaseId == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        return purchaseService.myRemoveById(purchaseId);
    }

    @PostMapping("/list/page")
    public Result<Page<PurchaseVO>> listPage(@RequestBody PurchaseQueryRequest purchaseQueryRequest) {
        if (purchaseQueryRequest == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        long pageNum = purchaseQueryRequest.getCurrent();
        long pageSize = purchaseQueryRequest.getPageSize();
        if (pageNum <= 0 || pageSize <= 0) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        // 限制爬虫
        ThrowUtils.throwIf(pageSize > 20, HttpCodeEnum.PARAMS_ERROR);
        Page<Purchase> page = purchaseService.page(new Page<>(pageNum, pageSize),
                purchaseService.getQueryWrapper(purchaseQueryRequest));
        // 根据用户id获取用户名和根据商品id获取商品名 并封装到VO中
        List<PurchaseVO> purchaseVOList = page.getRecords().stream().map(purchase -> {
            PurchaseVO purchaseVO = new PurchaseVO();
            BeanUtils.copyProperties(purchase, purchaseVO);
            purchaseVO.setUserName(userService.getById(purchase.getUserId()).getUserName());
            purchaseVO.setProductName(productService.getById(purchase.getProductId()).getProductName());
            return purchaseVO;
        }).collect(Collectors.toList());
        // 将list封装到page中
        Page<PurchaseVO> purchaseVOPage = new Page<>(pageNum, pageSize, page.getTotal());
        purchaseVOPage.setRecords(purchaseVOList);
        return Result.success(purchaseVOPage);
    }
}

