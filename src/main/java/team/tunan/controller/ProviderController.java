package team.tunan.controller;

import org.springframework.web.bind.annotation.*;
import team.tunan.common.HttpCodeEnum;
import team.tunan.common.Result;
import team.tunan.exception.BusinessException;
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

}