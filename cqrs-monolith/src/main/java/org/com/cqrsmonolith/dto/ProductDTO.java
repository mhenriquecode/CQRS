package org.com.cqrsmonolith.dto;

import org.com.cqrsmonolith.domain.Product;

public record ProductDTO(
         long id,
         String name,
         String description,
         double price


) {
    public static ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public static Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.id());
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        return product;
    }
}

