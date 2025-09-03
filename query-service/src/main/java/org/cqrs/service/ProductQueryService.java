package org.cqrs.service;

import org.cqrs.entity.Product;
import org.cqrs.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.cqrs.dto.ProductEvent;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findOne(long id){
        Product existingProduct = productRepository.findById(id).get();
        return existingProduct;
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
