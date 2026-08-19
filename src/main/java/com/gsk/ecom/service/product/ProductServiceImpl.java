package com.gsk.ecom.service.product;

import com.gsk.ecom.handler.product.ProductException;
import com.gsk.ecom.mapper.product.ProductMapper;
import com.gsk.ecom.model.product.ProductRequest;
import com.gsk.ecom.model.product.ProductResponse;
import com.gsk.ecom.model.purchase.ProductPurchaseRequest;
import com.gsk.ecom.model.purchase.ProductPurchaseResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final Map<Integer, ProductResponse> products;

    private static Integer productId = 1;

    ProductServiceImpl() {
        products = new HashMap<>();
        products.put(productId, new ProductResponse(productId++, "iPhone", 150000.2d, "electronics", 10));
        products.put(productId, new ProductResponse(productId++, "iMac", 2500000.2d, "electronics", 15));
        products.put(productId, new ProductResponse(productId++, "iPad", 30000.2d, "electronics", 8));
    }

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductResponse> findAll() {
        return products.values().stream().map(productMapper::getProductResponse).collect(Collectors.toList());
    }

    @Override
    public String addProduct(ProductRequest productRequest) {
        productRequest.setProductId(productId);
        products.put(productId, productMapper.dtoToEntity(productRequest));
        productId++;
        return productRequest.toString();
    }

    @Override
    public ProductResponse findById(Integer productId) {
        System.out.println("products.get("+productId+") :: "+products.get(productId));
        if(products.get(productId) == null){
            throw new ProductException("Product not Found for ID :: "+productId);
        }
        return products.get(productId);
    }

    @Override
    public @Nullable List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> purchaseRequest) {
        var requestedProductIds = purchaseRequest.stream().map(ProductPurchaseRequest::productId).collect(Collectors.toSet());
        var storedProductIds = requestedProductIds.stream().map(key -> {
            if(!products.containsKey(key)){
                throw new ProductException("Product with key :: "+key+" is not available");
            }
                    return products.get(key).getProductId();
        }).collect(Collectors.toSet());

        var purchaseList = purchaseRequest.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).collect(Collectors.toList());
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (ProductPurchaseRequest productPurchaseRequest:purchaseList){
            var requestedQuantity = productPurchaseRequest.quantity();
            var product = products.get(productPurchaseRequest.productId());
            var availQuantity = product.getAvailQuantity();
            if(requestedQuantity > availQuantity){
                throw new ProductException("The Requested quantity : "+requestedQuantity+" for Product "+productPurchaseRequest.productId()+" is not available");
            }
            var remainingQuantity = availQuantity - requestedQuantity;
            product.setAvailQuantity(remainingQuantity);
            products.put(product.getProductId(), product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product));
        }
        return purchasedProducts;
    }
}
