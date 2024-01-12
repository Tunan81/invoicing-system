package team.tunan.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import team.tunan.model.dto.ProviderQueryRequest;
import team.tunan.model.entity.Provider;

public interface IProviderService extends IService<Provider> {
    Wrapper<Provider> getQueryWrapper(ProviderQueryRequest providerQueryRequest);
}
