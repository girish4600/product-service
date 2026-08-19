package com.gsk.ecom.controller.product;

import com.gsk.ecom.model.product.ProductRequest;
import com.gsk.ecom.model.product.ProductResponse;
import com.gsk.ecom.model.purchase.ProductPurchaseRequest;
import com.gsk.ecom.model.purchase.ProductPurchaseResponse;
import com.gsk.ecom.service.product.ProductService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        List<ProductResponse> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductRequest productRequest){
        String responses = productService.addProduct(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findById(@PathVariable(name = "productId") Integer productId){
        return new ResponseEntity<>(productService.findById(productId), HttpStatus.FOUND);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> addProduct(@RequestBody List<ProductPurchaseRequest> purchaseRequest){
        return ResponseEntity.ok(productService.purchaseProduct(purchaseRequest));
    }

}
