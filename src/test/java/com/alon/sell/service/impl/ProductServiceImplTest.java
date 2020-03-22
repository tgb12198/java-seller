package com.alon.sell.service.impl;

import com.alibaba.fastjson.JSON;
import com.alon.sell.dataobject.ProductInfo;
import com.alon.sell.enums.ProductStatusEnum;
import com.alon.sell.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findById() {
        ProductInfo product = productService.findById("123456");
        System.out.println(JSON.toJSONString(product));
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfos = productService.findUpAll();
        System.out.println(JSON.toJSONString(productInfos));
    }

    @Test
    public void findProductInfosByProductStatus() {
        List<ProductInfo> productInfos = productService.findProductInfosByProductStatus(ProductStatusEnum.DOWN.getCode());
        System.out.println(JSON.toJSONString(productInfos));
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0, 10);
        Page<ProductInfo> productInfos = productService.findAll(request);
        System.out.println(JSON.toJSONString(productInfos));
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("789123");
        productInfo.setProductName("酸辣粉");
        productInfo.setProductPrice(new BigDecimal(10.0));
        productInfo.setProductStock(100);
        productInfo.setProductIcon("slf.jpg");
        productInfo.setCategoryType(2);
        productInfo.setProductStatus(0);
        productInfo.setProductDescription("简直是美味啊,酸爽！");

        ProductInfo product = productService.save(productInfo);
        System.out.println(JSON.toJSONString(product));
    }
}