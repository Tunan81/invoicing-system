package team.tunan.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import team.tunan.common.HttpCodeEnum;
import team.tunan.common.Result;
import team.tunan.model.dto.sale.SaleAddDTO;
import team.tunan.model.dto.sale.SaleQueryRequest;
import team.tunan.model.entity.Sale;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tunan
 * @since 2023-11-29
 */
public interface ISaleService extends IService<Sale> {

    Wrapper<Sale> getQueryWrapper(SaleQueryRequest saleQueryRequest);

    Result<?> saveDate(SaleAddDTO saleAddDTO);
}
