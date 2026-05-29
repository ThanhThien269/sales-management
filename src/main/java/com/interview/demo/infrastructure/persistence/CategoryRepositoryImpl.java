package com.interview.demo.infrastructure.persistence;

import com.interview.demo.domain.entities.database.Category;
import com.interview.demo.domain.repositories.CategoryRepository;
import com.interview.demo.infrastructure.persistence.jpa.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryJpaRepository jpaRepository;

    @Override
    public Category save(Category category) {
        return jpaRepository.save(category);
    }

    @Override
    public List<Category> saveAll(Iterable<Category> categories) {
        return jpaRepository.saveAll(categories);
    }

    @Override
    public Category findById(Integer id) {
        return jpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> findByIds(Iterable<Integer> ids) {
        return jpaRepository.findAllById(ids);
    }

    @Override
    public List<Category> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void delete(Category category) {
        jpaRepository.delete(category);
    }

    @Override
    public void deleteByIds(Iterable<Integer> ids) {
        jpaRepository.deleteAllById(ids);
    }

    @Override
    public boolean existsById(Integer id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public void flush() {
        jpaRepository.flush();
    }
}

