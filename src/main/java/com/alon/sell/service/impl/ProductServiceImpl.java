package com.alon.sell.service.impl;

import com.alon.sell.dao.ProductInfoDao;
import com.alon.sell.dataobject.ProductInfo;
import com.alon.sell.dto.Cart;
import com.alon.sell.enums.ProductStatusEnum;
import com.alon.sell.enums.ResultEnum;
import com.alon.sell.exception.SellException;
import com.alon.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：Alon
 * @date ：Created in 2020/1/31 12:46
 * @description：商品
 * @modified By：
 * @version: v1.0.0.1$
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfo findById(String productId) {
        ProductInfo product = productInfoDao.findById(productId).get();
        return product;
    }

    @Override
    public List<ProductInfo> findUpAll() {
        List<ProductInfo> productInfos = productInfoDao.findProductInfosByProductStatus(ProductStatusEnum.UP.getCode());
        return productInfos;
    }

    @Override
    public List<ProductInfo> findProductInfosByProductStatus(Integer productStatus) {
        return productInfoDao.findProductInfosByProductStatus(productStatus);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        Page<ProductInfo> productInfos = productInfoDao.findAll(pageable);
        return productInfos;
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {

        return productInfoDao.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<Cart> cartList) {
        cartList.forEach(item -> {
            ProductInfo productInfo = productInfoDao.findById(item.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + item.getProductQuantity();
            productInfo.setProductStock(result);

            productInfoDao.save(productInfo);
        });
    }

    @Override
    @Transactional
    public void decreaseStock(List<Cart> cartList) {
        cartList.forEach(item -> {
            ProductInfo productInfo = productInfoDao.findById(item.getProductId()).get();
            Integer result = productInfo.getProductStock() - item.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_INSUFFICIENT);
            }
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        });
    }
}
