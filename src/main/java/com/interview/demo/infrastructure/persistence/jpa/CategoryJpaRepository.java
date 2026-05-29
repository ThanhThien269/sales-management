package com.interview.demo.infrastructure.persistence.jpa;

import com.interview.demo.domain.entities.database.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, Integer> {
}

