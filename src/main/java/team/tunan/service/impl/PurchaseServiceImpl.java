package team.tunan.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import team.tunan.mapper.PurchaseMapper;
import team.tunan.model.dto.purchase.PurchaseQueryRequest;
import team.tunan.model.entity.Purchase;
import team.tunan.model.vo.PurchaseVO;
import team.tunan.service.IPurchaseService;

import org.springframework.stereotype.Service;

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

    @Override
    public Wrapper<Purchase> getQueryWrapper(PurchaseQueryRequest purchaseQueryRequest) {
        if (purchaseQueryRequest == null) {
            return null;
        }
        Long id = purchaseQueryRequest.getId();
        Long userId = purchaseQueryRequest.getUserId();
        Date purchaseTime = purchaseQueryRequest.getPurchaseTime();
        QueryWrapper<Purchase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(userId != null, "user_id", userId);
        queryWrapper.eq(purchaseTime != null, "purchase_time", purchaseTime);
        return queryWrapper;
    }
}
