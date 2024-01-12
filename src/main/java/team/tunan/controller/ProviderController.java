package team.tunan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import team.tunan.common.HttpCodeEnum;
import team.tunan.common.Result;
import team.tunan.exception.BusinessException;
import team.tunan.model.dto.ProviderQueryRequest;
import team.tunan.model.entity.Provider;
import team.tunan.service.IProviderService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/provider/")
@CrossOrigin(allowCredentials = "true")
public class ProviderController {

    @Resource
    private IProviderService iProviderService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Provider provider) {
        if (provider == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        return Result.success(iProviderService.save(provider));
    }

    @DeleteMapping("/delete/{providerId}")
    public Result<?> delete(@PathVariable("providerId") Long providerId) {
        if (providerId == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        return Result.success(iProviderService.removeById(providerId));
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Provider provider) {
        if (provider == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        return Result.success(iProviderService.updateById(provider));
    }

    @PostMapping("/list/page")
    public Result<Page<Provider>> listPage(@RequestBody ProviderQueryRequest providerQueryRequest) {
        if (providerQueryRequest == null) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        long pageNum = providerQueryRequest.getCurrent();
        long pageSize = providerQueryRequest.getPageSize();
        if (pageNum <= 0 || pageSize <= 0) {
            throw new BusinessException(HttpCodeEnum.PARAMS_ERROR);
        }
        Page<Provider> page = iProviderService.page(new Page<>(pageNum, pageSize),
                iProviderService.getQueryWrapper(providerQueryRequest));
        return Result.success(page);
    }

}