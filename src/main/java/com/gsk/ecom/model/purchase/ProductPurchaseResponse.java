package com.gsk.ecom.model.purchase;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductPurchaseResponse(

        Integer id,
        String productName,
        Integer quantity,
        Double price
) {
}
