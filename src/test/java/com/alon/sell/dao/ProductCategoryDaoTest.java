package com.alon.sell.dao;

import com.alon.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void findOneTest() {
        Optional<ProductCategory> product = productCategoryDao.findById(1);
        System.out.println(product.toString());
    }

    @Test
    public void SaveDate() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("热销榜");
        productCategory.setCategoryType(2);
        productCategoryDao.save(productCategory);
    }

    @Test
    public void UpdateTest() {
        Optional<ProductCategory> product = productCategoryDao.findById(2);
        ProductCategory productCategory = product.get();
        productCategory.setCategoryType(2);
        productCategoryDao.save(productCategory);
    }

    @Test
    public void findProductCategoriesByCategoryTypeIn() {
        List<Integer> categoryList = Arrays.asList(1, 2, 3);
        List<ProductCategory> result = productCategoryDao.findProductCategoriesByCategoryTypeIn(categoryList);
        System.out.println(result);
    }
}