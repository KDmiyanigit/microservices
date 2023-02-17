package com.naruto.orderservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderLineItemDTO {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
