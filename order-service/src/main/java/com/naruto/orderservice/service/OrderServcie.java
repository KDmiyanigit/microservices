package com.naruto.orderservice.service;


import com.naruto.orderservice.dto.InventoryResponse;
import com.naruto.orderservice.dto.OrderLineItemDTO;
import com.naruto.orderservice.dto.OrderRequest;
import com.naruto.orderservice.model.Order;
import com.naruto.orderservice.model.OrderLineItems;
import com.naruto.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class OrderServcie {

    private final OrderRepository orderRepository;
    private final WebClient webClient;


    public Order placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDo)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode).toList();


        //calling the inventory service, to see product is in stock or not
        //to rrad the data we need to add this method cmplsry bodytoMono
        //to make it sync service we need to add block()

        InventoryResponse[] inventoryResponsArray = webClient.get()
                .uri("http://localhost:8100/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()

                .bodyToMono(InventoryResponse[].class)

                .block();

       /* boolean allProductsInStock = Arrays.stream(inventoryResponsArray)
                .allMatch(InventoryResponse::isInStock);*/

        boolean allProductsInStock = Arrays.stream(inventoryResponsArray)
                .filter(InventoryResponse::isInStock)
                .map(InventoryResponse::getSkuCode)
                .collect(Collectors.toList())
                .containsAll(skuCodes);



        if (allProductsInStock) {
            return orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("product is not in stock");
        }

    }

    private OrderLineItems mapToDo(OrderLineItemDTO orderLineItemDTO) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemDTO.getPrice());
        orderLineItems.setQuantity(orderLineItemDTO.getQuantity());
        orderLineItems.setSkuCode(orderLineItemDTO.getSkuCode());
        return orderLineItems;

    }

}
