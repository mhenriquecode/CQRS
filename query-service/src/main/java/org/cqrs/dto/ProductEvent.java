package org.cqrs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cqrs.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {
    private String eventType;

    private Product product;
}
