package com.gsk.ecom.handler.product;

import lombok.Builder;

@Builder
public record ErrorResponse (
    int status,
    String message,
    long timestamp){
}
