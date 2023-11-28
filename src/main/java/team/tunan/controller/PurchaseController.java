package team.tunan.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.tunan.service.IPurchaseService;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Tunan
 * @since 2023-11-29
 */
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Resource
    private IPurchaseService purchaseService;


}

