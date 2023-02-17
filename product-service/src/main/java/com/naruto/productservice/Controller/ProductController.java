package com.naruto.productservice.Controller;

import com.naruto.productservice.model.Product;
import com.naruto.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
  @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
     return new ResponseEntity<Product>(productService.saveProducts(product),HttpStatus.CREATED);

    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Product> allProduct(){
        return productService.getProducts();

    }


}
