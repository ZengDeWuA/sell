package com.my.shell.controller;

import com.my.shell.VO.ResultVO;
import com.my.shell.converter.OrderForm2OrderDTOConverter;
import com.my.shell.dto.OrderDTO;
import com.my.shell.enums.ResultEnum;
import com.my.shell.exception.SellException;
import com.my.shell.form.OrderForm;
import com.my.shell.service.OrderService;
import com.my.shell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/sell/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    //创建订单
    @PostMapping("create")
    public ResultVO<Map<String,String >> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String > map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        ResultVO<Map<String,String >> resultVO = ResultVOUtil.success(map);
        return resultVO;
    }
    //查询列表
    //查询单个
    //取消订单
}
