package team.tunan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.tunan.mapper.ProviderMapper;
import team.tunan.model.entity.Provider;
import team.tunan.service.IProviderService;

@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements IProviderService {
}
