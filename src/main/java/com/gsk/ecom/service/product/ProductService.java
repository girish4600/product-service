package com.gsk.ecom.service.product;

import com.gsk.ecom.model.product.ProductRequest;
import com.gsk.ecom.model.product.ProductResponse;
import com.gsk.ecom.model.purchase.ProductPurchaseRequest;
import com.gsk.ecom.model.purchase.ProductPurchaseResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();

    String addProduct(ProductRequest productRequest);

    ProductResponse findById(Integer productId);

    @Nullable List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> purchaseRequest);
}
