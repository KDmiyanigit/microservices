package com.naruto.orderservice.controller;


import com.naruto.orderservice.dto.OrderRequest;
import com.naruto.orderservice.model.Order;
import com.naruto.orderservice.service.OrderServcie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderServcie orderServcie;



    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Order placeOrder(@RequestBody OrderRequest orderRequest) {
      return   orderServcie.placeOrder(orderRequest);

    }
}
