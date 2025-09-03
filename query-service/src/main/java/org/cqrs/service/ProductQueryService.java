package org.cqrs.service;

import org.cqrs.entity.Product;
import org.cqrs.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.cqrs.dto.ProductEvent;

import java.util.List;

@Service
public class ProductQueryService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }


}
