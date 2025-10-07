package org.com.cqrsmonolith.command.commandService;

import org.com.cqrsmonolith.domain.Product;
import org.com.cqrsmonolith.dto.ProductDTO;
import org.com.cqrsmonolith.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.com.cqrsmonolith.dto.ProductDTO.toDTO;
import static org.com.cqrsmonolith.dto.ProductDTO.toEntity;


@Service
public class CommandService {

    private final ProductRepository repository;

    public CommandService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductDTO create(ProductDTO productDTO){
        Product newProduct = toEntity(productDTO);
        Product savedProduct = repository.save(newProduct);
        return toDTO(savedProduct);
    }

    public ProductDTO update(ProductDTO productDTO){
        Product existing = repository.findById(productDTO.id())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + productDTO.id()));

        existing.setName(productDTO.name());
        existing.setDescription(productDTO.description());
        existing.setPrice(productDTO.price());

        Product updatedProduct = repository.save(existing);
        return toDTO(updatedProduct);
    }

    public void delete(long id){
        Product existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
        repository.delete(existing);
    }

}
