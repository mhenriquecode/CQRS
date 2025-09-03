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

    @KafkaListener(topics = "product-event-topic", groupId = "product-event-group")
    public void processProductEvents(ProductEvent productEvent){
        Product product = productEvent.getProduct();
        if (productEvent.getEventType().equals("CreateProduct")){
            productRepository.save(productEvent.getProduct());
        }
        if (productEvent.getEventType().equals("UpdateProduct")){
            Product existingProduct = productRepository.findById(product.getId()).get();
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            productRepository.save(existingProduct);
        }
        if (productEvent.getEventType().equals("DeleteProduct")) {
            productRepository.deleteById(product.getId());
        }

    }
}
