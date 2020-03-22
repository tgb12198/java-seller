package com.alon.sell.dao;

import com.alon.sell.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("皮蛋瘦肉粥");
        productInfo.setProductPrice(new BigDecimal(8.8));
        productInfo.setProductStock(100);
        productInfo.setProductIcon("pd.jpg");
        productInfo.setCategoryType(1);
        productInfo.setProductStatus(0);

        ProductInfo product = productInfoDao.save(productInfo);
        System.out.println(product.toString());
    }

    @Test
    public void findProductInfosByProductStatus() {
        List<ProductInfo> productInfos = productInfoDao.findProductInfosByProductStatus(0);
        System.out.println(productInfos.toString());
    }

    @Test
    public void update(){
        ProductInfo productInfo = productInfoDao.findById("123456").get();
        productInfo.setProductDescription("很好喝的粥");

        productInfoDao.save(productInfo);
    }
}