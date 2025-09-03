package org.cqrs.controller;

import org.cqrs.dto.ProductEvent;
import org.cqrs.entity.Product;
import org.cqrs.service.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductCommandController {
    @Autowired
    private ProductCommandService productCommandService;

    @PostMapping
    public Product createProduct(@RequestBody ProductEvent productEvent) {
        return productCommandService.createProduct(productEvent);
    }

}
