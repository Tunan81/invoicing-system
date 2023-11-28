package team.tunan.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import team.tunan.mapper.PurchaseMapper;
import team.tunan.model.entity.Purchase;
import team.tunan.service.IPurchaseService;

import org.springframework.stereotype.Service;

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

}
