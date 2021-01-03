package org.example.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductInfo {
    private Integer productId;
    private Integer store;
}
