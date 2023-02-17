package com.naruto.productservice.service;

import com.naruto.productservice.model.Product;
import com.naruto.productservice.repository.ProductRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {


    @Autowired
    private final ProductRespository productRespository;

    public Product saveProducts(Product product){
        log.info("Product number {} Added to data base"+product.getId());
        return productRespository.save(product);

    }

    public List<Product> getProducts(){

        return productRespository.findAll();
    }
}
