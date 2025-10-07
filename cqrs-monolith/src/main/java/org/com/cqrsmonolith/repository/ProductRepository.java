package org.com.cqrsmonolith.repository;

import org.com.cqrsmonolith.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
