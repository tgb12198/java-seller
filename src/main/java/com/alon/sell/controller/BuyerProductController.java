package com.alon.sell.controller;

import com.alon.sell.dataobject.ProductCategory;
import com.alon.sell.dataobject.ProductInfo;
import com.alon.sell.service.CategoryService;
import com.alon.sell.service.ProductService;
import com.alon.sell.utils.ResultUtils;
import com.alon.sell.viewobject.Product;
import com.alon.sell.viewobject.ProductViewModel;
import com.alon.sell.viewobject.ResultModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：Alon
 * @date ：Created in 2020/1/31 17:49
 * @description：买家商品
 * @modified By：
 * @version: v1.0.0.1$
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultModel list() {
        ResultModel model = new ResultModel();
        //查询所有商家商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //查询类目
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findProductCategoriesByCategoryTypeIn(categoryTypeList);
        //数据拼装
        List<ProductViewModel> productViewModelList = new ArrayList<>();
        productCategoryList.forEach(item -> {
            ProductViewModel productViewModel = new ProductViewModel();
            productViewModel.setCategoryName(item.getCategoryName());
            productViewModel.setCategoryType(item.getCategoryType());
            List<Product> productList = new ArrayList<>();
            productInfoList.forEach(p -> {
                if (p.getCategoryType().equals(item.getCategoryType())) {
                    Product product = new Product();
                    BeanUtils.copyProperties(p, product);
                    productList.add(product);
                }
            });
            productViewModel.setProductInfoList(productList);
            productViewModelList.add(productViewModel);
        });
        return ResultUtils.success(productViewModelList);
    }
}
