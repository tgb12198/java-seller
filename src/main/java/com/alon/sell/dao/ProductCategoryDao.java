package com.alon.sell.dao;

import com.alon.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ：Alon
 * @date ：Created in 2020/1/30 9:49
 * @description：类目
 * @modified By：
 * @version: v1.0.0.1$
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    /**
     * 通过类目编号获取类目信息
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findProductCategoriesByCategoryTypeIn(List<Integer> categoryTypeList);
}
