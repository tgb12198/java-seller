package com.alon.sell.controller;

import com.alon.sell.converter.ConvertUtils;
import com.alon.sell.dto.OrderInfo;
import com.alon.sell.enums.ResultEnum;
import com.alon.sell.exception.SellException;
import com.alon.sell.forms.OrderForm;
import com.alon.sell.service.BuyerService;
import com.alon.sell.service.OrderMasterService;
import com.alon.sell.utils.ResultUtils;
import com.alon.sell.viewobject.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/2 22:50
 * @description：买家订单
 * @modified By：
 * @version: v1.0.0.1$
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     *
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultModel<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数异常，orderForm:{}", orderForm);
            throw new SellException(ResultEnum.PARAMS_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderInfo orderInfo = ConvertUtils.convert2OrderInfo(orderForm);
        if (CollectionUtils.isEmpty(orderInfo.getOrderDetailList())) {
            log.error("【创建订单】商品为空，order:{}", orderForm.getItems());
            throw new SellException(ResultEnum.ORDER_CART_EMPTY);
        }
        OrderInfo orderResult = orderMasterService.create(orderInfo);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderResult.getOrderId());

        return ResultUtils.success(map);
    }

    /**
     * 查询订单列表
     *
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultModel<List<OrderInfo>> list(@RequestParam("openid") String openid,
                                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【订单列表】查询订单列表，openid不能为空");
            throw new SellException(ResultEnum.PARAMS_ERROR);
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderInfo> orderInfoPage = orderMasterService.findList(openid, pageRequest);
        return ResultUtils.success(orderInfoPage.getContent());
    }

    /**
     * 订单详情
     *
     * @param openid
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ResultModel<OrderInfo> detail(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)) {
            log.error("【订单详情】查询订单详情，openid或orderId不能为空");
            throw new SellException(ResultEnum.PARAMS_ERROR);
        }
        OrderInfo orderInfo = buyerService.findOrderOne(openid, orderId);
        return ResultUtils.success(orderInfo);
    }

    /**
     * 订单取消
     *
     * @param openid
     * @param orderId
     * @return
     */
    @PostMapping("/cancel")
    public ResultModel<OrderInfo> cancel(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)) {
            log.error("【订单取消】订单取消失败，openid或orderId不能为空");
            throw new SellException(ResultEnum.PARAMS_ERROR);
        }
        buyerService.cancelOrder(openid, orderId);
        return ResultUtils.success();
    }
}
