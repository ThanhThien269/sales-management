package com.interview.demo.domain.repositories;

import com.interview.demo.common.repositories.CommonDbEntityRepository;
import com.interview.demo.domain.entities.database.Product;
import com.interview.demo.domain.entities.request_dto.product.ProductFilterOption;
import com.interview.demo.domain.entities.response_dto.product.ProductResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends CommonDbEntityRepository<Product, UUID> {

    List<ProductResponse> findByFilterOptions(ProductFilterOption filter, Integer page, Integer size);

}

