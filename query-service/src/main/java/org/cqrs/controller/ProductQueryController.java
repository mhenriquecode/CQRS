package org.cqrs.controller;

import org.cqrs.entity.Product;
import org.cqrs.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    @Autowired
    private ProductQueryService productQueryService;

    @GetMapping
    public List<Product> fetchAllProducts() {
        return productQueryService.findAll();
    }

    @GetMapping("/{id}")
    public Product findOneProduct(@PathVariable long id){
        return productQueryService.findOne(id);
    }
}
