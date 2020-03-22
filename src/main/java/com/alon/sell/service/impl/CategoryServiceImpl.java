package com.alon.sell.service.impl;

import com.alon.sell.dao.ProductCategoryDao;
import com.alon.sell.dataobject.ProductCategory;
import com.alon.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Alon
 * @date ：Created in 2020/1/30 19:49
 * @description：商品类目
 * @modified By：
 * @version: v1.0.0.1$
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory findById(Integer categoryId) {
        ProductCategory category = productCategoryDao.findById(categoryId).get();
        return category;
    }

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> productCategories = productCategoryDao.findAll();
        return productCategories;
    }

    @Override
    public List<ProductCategory> findProductCategoriesByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<ProductCategory> categories = productCategoryDao.findProductCategoriesByCategoryTypeIn(categoryTypeList);
        return categories;
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        ProductCategory category = productCategoryDao.save(productCategory);
        return category;
    }
}
