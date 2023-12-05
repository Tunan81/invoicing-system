package team.tunan.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import team.tunan.common.HttpCodeEnum;
import team.tunan.common.Result;
import team.tunan.exception.BusinessException;
import team.tunan.exception.ThrowUtils;
import team.tunan.model.dto.sale.SaleAddDTO;
import team.tunan.model.dto.sale.SaleQueryRequest;
import team.tunan.model.entity.Product;
import team.tunan.model.entity.Sale;
import team.tunan.model.entity.User;
import team.tunan.model.vo.SaleVO;
import team.tunan.service.IProductService;
import team.tunan.service.ISaleService;
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
@RestController
@RequestMapping("/sale")
@CrossOrigin(allowCredentials = "true")
public class SaleController {

    @Resource
    private ISaleService saleService;

    @Resource
    private IProductService productService;

    @Resource
    private UserService userService;

    @PostMapping("/list/page")
    public Result<Page<SaleVO>> listPage(@RequestBody SaleQueryRequest saleQueryRequest) {
        if (saleQueryRequest == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        long pageNum = saleQueryRequest.getCurrent();
        long pageSize = saleQueryRequest.getPageSize();
        if (pageNum <= 0 || pageSize <= 0) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        // 限制爬虫
        ThrowUtils.throwIf(pageSize > 20, HttpCodeEnum.PARAMS_ERROR);
        Page<Sale> page = saleService.page(new Page<>(pageNum, pageSize),
               saleService.getQueryWrapper(saleQueryRequest));
        List<SaleVO> saleVOList = page.getRecords().stream().map(sale -> {
            SaleVO saleVO = new SaleVO();
            BeanUtils.copyProperties(sale, saleVO);
            Product product = productService.getById(sale.getProductId());
            saleVO.setProductName(product.getProductName());
            User user = userService.getById(sale.getUserId());
            saleVO.setUserName(user.getUserName());
            return saleVO;
        }).collect(Collectors.toList());
        Page<SaleVO> saleVOPage = new Page<>();
        BeanUtils.copyProperties(page, saleVOPage);
        saleVOPage.setRecords(saleVOList);
        return Result.success(saleVOPage);
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody SaleAddDTO saleAddDTO) {
        if (saleAddDTO == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        return saleService.saveDate(saleAddDTO);
    }
}

