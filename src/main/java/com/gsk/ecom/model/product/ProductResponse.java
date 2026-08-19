package com.gsk.ecom.model.product;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ProductResponse {
    private Integer productId;
    private String productName;
    private Double productPrice;
    private String productCategory;
    private Integer availQuantity;
}
