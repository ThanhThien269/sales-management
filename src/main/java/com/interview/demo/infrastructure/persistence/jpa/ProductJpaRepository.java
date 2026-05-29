package com.interview.demo.infrastructure.persistence.jpa;

import com.interview.demo.domain.entities.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<Product, UUID> {

}
