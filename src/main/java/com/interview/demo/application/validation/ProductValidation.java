package com.interview.demo.application.validation;

import com.interview.demo.core.validator.exception.DataNotFoundException;
import com.interview.demo.domain.entities.database.Category;
import com.interview.demo.domain.entities.database.Product;
import com.interview.demo.domain.repositories.CategoryRepository;
import com.interview.demo.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductValidation {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Tìm product theo id, ném exception nếu không tồn tại.
     */
    public Product validateProductThenReturn(UUID id) {
        Product product = productRepository.findById(id);

        if (product == null)
            throw new DataNotFoundException(
                    String.format("Product with ID %s not found", id)
            );
        return product;
    }


    /**
     * Kiểm tra category tồn tại theo categoryId.
     * Ném exception nếu không tìm thấy.
     */
    public void validateCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId);

        if (category == null)
            throw new DataNotFoundException(
                    String.format("Category with ID %s not found", categoryId)
            );


    }

}
