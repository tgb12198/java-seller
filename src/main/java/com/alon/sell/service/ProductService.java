package com.alon.sell.service;

import com.alon.sell.dataobject.ProductInfo;
import com.alon.sell.dto.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author ：Alon
 * @date ：Created in 2020/1/31 12:36
 * @description：商品
 * @modified By：
 * @version: v1.0.0.1$
 */
public interface ProductService {

    ProductInfo findById(String productId);

    List<ProductInfo> findUpAll();

    List<ProductInfo> findProductInfosByProductStatus(Integer productStatus);

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<Cart> cartList);

    //减库存
    void decreaseStock(List<Cart> cartList);
}
