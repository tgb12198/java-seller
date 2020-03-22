package com.alon.sell.service.impl;

import com.alon.sell.dataobject.ProductCategory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findById() {
        ProductCategory category = categoryService.findById(1);
        System.out.println(category.toString());
    }

    @Test
    public void findAll() {
        List<ProductCategory> categories = categoryService.findAll();
        System.out.println(categories.toString());
    }

    @Test
    public void findProductCategoriesByCategoryTypeIn() {
        List<Integer> categoryList = Arrays.asList(1, 2, 3);
        List<ProductCategory> categories = categoryService.findProductCategoriesByCategoryTypeIn(categoryList);
        System.out.println(categories.toString());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(3);
        ProductCategory category = categoryService.save(productCategory);
        System.out.println(category.toString());
    }
}