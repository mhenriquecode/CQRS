package org.com.cqrsmonolith.query.queryService;

import org.com.cqrsmonolith.domain.Product;
import org.com.cqrsmonolith.dto.ProductDTO;
import org.com.cqrsmonolith.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import static org.com.cqrsmonolith.dto.ProductDTO.toDTO;

@Service
public class QueryService {
    private final ProductRepository repository;

    public QueryService(ProductRepository repository) {
        this.repository = repository;
    }


    public List<ProductDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(ProductDTO::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO findById(long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
        return toDTO(product);
    }

}
