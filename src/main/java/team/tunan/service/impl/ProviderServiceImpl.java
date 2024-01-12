package team.tunan.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.tunan.mapper.ProviderMapper;
import team.tunan.model.dto.ProviderQueryRequest;
import team.tunan.model.entity.Provider;
import team.tunan.service.IProviderService;

@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements IProviderService {
    @Override
    public Wrapper<Provider> getQueryWrapper(ProviderQueryRequest providerQueryRequest) {
        if (providerQueryRequest == null) {
            return null;
        }
        Long id = providerQueryRequest.getProviderId();
        String name = providerQueryRequest.getProviderName();
        QueryWrapper<Provider> wrapper = new QueryWrapper<>();
        query().eq(id != null,"provider_id",id);
        query().eq(name!= null,"provider_name",name);
        return wrapper;
    }
}
