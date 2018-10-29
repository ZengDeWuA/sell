package com.my.shell.dao;

import com.my.shell.dataObject.ProductCategory;
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
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOne(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("asasfasf");
        productCategory.setCategoryType(1);
        productCategoryRepository.save(productCategory);
//        Optional<ProductCategory> productCategory = productCategoryRepository.findById(1);
//        System.out.printf(productCategory.toString());
    }
    @Test
    public void test(){
        List<Integer> list = Arrays.asList(1,2,3);
        productCategoryRepository.findByCategoryTypeIn(list);

    }
}