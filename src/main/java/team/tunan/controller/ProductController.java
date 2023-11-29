package team.tunan.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import team.tunan.common.HttpCodeEnum;
import team.tunan.common.Result;
import team.tunan.exception.BusinessException;
import team.tunan.model.dto.product.ProductQueryRequest;
import team.tunan.model.entity.Product;
import team.tunan.service.IProductService;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Tunan
 * @since 2023-11-29
 */
@RestController
@RequestMapping("/product")
@CrossOrigin(allowCredentials = "true")
public class ProductController {

    @Resource
    private IProductService productService;

    @PostMapping("/list/page")
    public Result<Page<Product>> listPage(@RequestBody ProductQueryRequest productQueryRequest) {
        if (productQueryRequest == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        long pageNum = productQueryRequest.getCurrent();
        long pageSize = productQueryRequest.getPageSize();
        if (pageNum <= 0 || pageSize <= 0) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        Page<Product> page = productService.page(new Page<>(pageNum, pageSize),
                productService.getQueryWrapper(productQueryRequest));
        return Result.success(page);
    }
}

