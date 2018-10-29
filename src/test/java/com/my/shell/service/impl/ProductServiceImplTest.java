package com.my.shell.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;
    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0, 2);
        productService.findAll(pageRequest);
    }

    @Test
    public void findUpAll() {
    }

    @Test
    public void save() {
    }
}