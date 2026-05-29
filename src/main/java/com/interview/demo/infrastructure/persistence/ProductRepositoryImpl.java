package com.interview.demo.infrastructure.persistence;

import com.interview.demo.domain.entities.database.Product;
import com.interview.demo.domain.entities.request_dto.product.ProductFilterOption;
import com.interview.demo.domain.entities.response_dto.product.ProductResponse;
import com.interview.demo.domain.repositories.ProductRepository;
import com.interview.demo.infrastructure.persistence.jpa.ProductJpaRepository;
import com.interview.demo.infrastructure.persistence.mybatis.ProductMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository jpaRepository;
    private final ProductMybatisRepository myBatisRepository;


    @Override
    public Product save(Product product) { return jpaRepository.save(product); }

    @Override
    public List<Product> saveAll(Iterable<Product> products) { return jpaRepository.saveAll(products); }

    @Override
    public Product findById(UUID id) { return jpaRepository.findById(id).orElse(null); }

    @Override
    public List<Product> findByIds(Iterable<UUID> ids) { return jpaRepository.findAllById(ids); }

    @Override
    public List<Product> findAll() { return jpaRepository.findAll(); }

    @Override
    public void deleteById(UUID id) { jpaRepository.deleteById(id); }

    @Override
    public void delete(Product product) { jpaRepository.delete(product); }

    @Override
    public void deleteByIds(Iterable<UUID> ids) { jpaRepository.deleteAllById(ids); }

    @Override
    public boolean existsById(UUID id) { return jpaRepository.existsById(id); }

    @Override
    public void flush() { jpaRepository.flush(); }

    @Override
    public List<ProductResponse> findByFilterOptions(ProductFilterOption filter, Integer page, Integer size) {
        return myBatisRepository.findByFilterOptions(filter, page, size);
    }
}
