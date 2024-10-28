package com.example.dto;/*
 * @author -Suraj Tiwari
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductEvent {
    private ProductDto product;
    private String eventType;

}
