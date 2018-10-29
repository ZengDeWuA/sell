package com.my.shell.dao;

import com.my.shell.dataObject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;
    @Test
    public void findByBuyerOpenid() {

        PageRequest pageRequest = new PageRequest(0, 10);
        Page<OrderMaster> page= repository.findByBuyerOpenid("grdg",pageRequest );
        System.out.println(page.getContent().size());
        assertNotNull(page);
    }
}