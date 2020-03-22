package com.alon.sell.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alon.sell.dataobject.OrderDetail;
import com.alon.sell.dataobject.OrderMaster;
import com.alon.sell.dto.Cart;
import com.alon.sell.dto.OrderInfo;
import com.alon.sell.enums.ResultEnum;
import com.alon.sell.exception.SellException;
import com.alon.sell.forms.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/2 19:39
 * @description：实体转换
 * @modified By：
 * @version: v1.0.0.1$
 */
@Slf4j
public class ConvertUtils {
    /**
     * 实体转换
     *
     * @param orderMaster
     * @return
     */
    public static OrderInfo convert2Model(OrderMaster orderMaster) {
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderMaster, orderInfo);
        return orderInfo;
    }

    /**
     * 集合转换
     *
     * @param orderMasterList
     * @return
     */
    public static List<OrderInfo> convert2List(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e -> convert2Model(e)).collect(Collectors.toList());
    }

    /**
     * @param orderForm
     * @return
     */
    public static OrderInfo convert2OrderInfo(OrderForm orderForm) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setBuyerName(orderForm.getName());
        orderInfo.setBuyerPhone(orderForm.getPhone());
        orderInfo.setBuyerAddress(orderForm.getAddress());
        orderInfo.setBuyerOpenid(orderForm.getOpenid());

        List<Cart> cartList = new ArrayList<>();
        try {
            cartList = JSONObject.parseObject(orderForm.getItems(), new TypeReference<List<Cart>>() {
            }.getType());
            //List<Cart> c = JSONObject.parseArray(orderForm.getItems(), Cart.class);
        } catch (Exception e) {
            log.error("【json转换】转换错误，jsonString:{}", orderForm.getItems());
            throw new SellException(ResultEnum.JSON_CONVERT_ERROR);
        }

        List<OrderDetail> orderDetailList = new ArrayList<>();
        cartList.forEach(item -> {
            OrderDetail detail = new OrderDetail();
            detail.setProductId(item.getProductId());
            detail.setProductQuantity(item.getProductQuantity());
            orderDetailList.add(detail);
        });
        orderInfo.setOrderDetailList(orderDetailList);
        return orderInfo;
    }
}
