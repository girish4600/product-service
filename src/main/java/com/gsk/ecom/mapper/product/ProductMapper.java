package com.gsk.ecom.mapper.product;

import com.gsk.ecom.model.product.ProductRequest;
import com.gsk.ecom.model.product.ProductResponse;
import com.gsk.ecom.model.purchase.ProductPurchaseResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {


    public ProductResponse getProductResponse(ProductResponse productResponse) {
        return productResponse;
    }

    public ProductResponse dtoToEntity(ProductRequest productRequest) {
        return ProductResponse.builder()
                .productId(productRequest.getProductId())
                .productName(productRequest.getProductName())
                .productPrice(productRequest.getProductPrice())
                .productCategory(productRequest.getProductCategory())
                .build();
    }

    public ProductPurchaseResponse toProductPurchaseResponse(ProductResponse product) {
        return ProductPurchaseResponse.builder()
                .id(product.getProductId())
                .productName(product.getProductName())
                .price(product.getProductPrice())
                .quantity(product.getAvailQuantity())
                .build();
    }
}
