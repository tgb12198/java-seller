package com.alon.sell.service;

import com.alon.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @author ：Alon
 * @date ：Created in 2020/1/30 19:46
 * @description：商品类目
 * @modified By：
 * @version: v1.0.0.1$
 */
public interface CategoryService {

    ProductCategory findById(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findProductCategoriesByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
