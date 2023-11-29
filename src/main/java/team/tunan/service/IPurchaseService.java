package team.tunan.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import team.tunan.model.dto.purchase.PurchaseQueryRequest;
import team.tunan.model.entity.Purchase;
import team.tunan.model.vo.PurchaseVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tunan
 * @since 2023-11-29
 */
public interface IPurchaseService extends IService<Purchase> {

    Wrapper<Purchase> getQueryWrapper(PurchaseQueryRequest purchaseQueryRequest);
}
