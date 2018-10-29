package com.my.shell.service.impl;

import com.my.shell.dao.ProductInfoRepository;
import com.my.shell.dataObject.ProductInfo;
import com.my.shell.dto.CartDTO;
import com.my.shell.enums.ProductStatusEnum;
import com.my.shell.enums.ResultEnum;
import com.my.shell.exception.SellException;
import com.my.shell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.getOne(productId);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList) {
            ProductInfo productInfo = repository.getOne(cartDTO.getProductId());
            if (null==productInfo){
                throw new SellException(ResultEnum.ORDER_NOT_EXIST);
            }
            Integer productStoke = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(productStoke);
            repository.save(productInfo);
        }
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO :cartDTOList ) {
            ProductInfo productInfo = repository.getOne(cartDTO.getProductId());
            if (null==productInfo){
                throw new SellException(ResultEnum.ORDER_NOT_EXIST);
            }
            Integer productStoke = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (productStoke<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(productStoke);
            repository.save(productInfo);
        }
    }
}
