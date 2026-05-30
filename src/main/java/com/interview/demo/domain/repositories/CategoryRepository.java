package com.interview.demo.domain.repositories;

import com.interview.demo.common.repositories.CommonDbEntityRepository;
import com.interview.demo.domain.entities.database.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CommonDbEntityRepository<Category, Integer> {
}

