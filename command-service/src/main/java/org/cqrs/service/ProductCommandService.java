package org.cqrs.service;

import org.cqrs.dto.ProductEvent;
import org.cqrs.entity.Product;
import org.cqrs.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    public Product createProduct(ProductEvent productEvent) {
        Product productDO =  repository.save(productEvent.getProduct());
        ProductEvent event = new ProductEvent("CreateProduct", productDO);

        kafkaTemplate.send("product-event-topic", event);
        return productDO;
    }

}
