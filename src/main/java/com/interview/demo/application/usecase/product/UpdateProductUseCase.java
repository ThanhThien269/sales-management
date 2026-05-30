package com.interview.demo.application.usecase.product;

import com.interview.demo.application.validation.ProductValidation;
import com.interview.demo.constant.database.ProductStatusEnum;
import com.interview.demo.constant.enumuration.MessageCode;
import com.interview.demo.common.usecase.UseCase;
import com.interview.demo.common.usecase.UseCaseContext;
import com.interview.demo.domain.entities.api.response.ApiResponse;
import com.interview.demo.domain.entities.api.response.FixedLocaleMessage;
import com.interview.demo.domain.entities.database.Product;
import com.interview.demo.domain.entities.request_dto.product.UpdateProductRequestBody;
import com.interview.demo.domain.repositories.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Transactional
@Component
@RequiredArgsConstructor
public class UpdateProductUseCase extends UseCase<UpdateProductUseCase.InputValue, ApiResponse, UseCaseContext> {

    private final ProductRepository productRepository;
    private final ProductValidation productValidation;

    @Override
    public ApiResponse execute(InputValue input, UseCaseContext context) {
        UUID productId = input.productId();
        UpdateProductRequestBody requestBody = input.requestBody();


        Product product = productValidation.validateProductThenReturn(productId);


        if (requestBody.getCategoryId() != null) {
            productValidation.validateCategory(requestBody.getCategoryId());
            product.setCategoryId(requestBody.getCategoryId());
        }

        if (requestBody.getName() != null)          product.setName(requestBody.getName());
        if (requestBody.getDescription() != null)   product.setDescription(requestBody.getDescription());
        if (requestBody.getPrice() != null)         product.setPrice(requestBody.getPrice());
        if (requestBody.getStockQuantity() != null) product.setQuantity(requestBody.getStockQuantity());
        if (requestBody.getImageUrl() != null)      product.setImage(requestBody.getImageUrl());
        if (requestBody.getStatusId() != null)      product.setStatus(ProductStatusEnum.fromId(requestBody.getStatusId()));

        Product updated = productRepository.save(product);

        return ApiResponse.builder()
                .result("success")
                .localeMessage(new FixedLocaleMessage(MessageCode.UPDATE_SUCCESSFULLY))
                .content(updated)
                .status(HttpStatus.OK)
                .build();
    }

    public record InputValue(
            UUID productId,
            UpdateProductRequestBody requestBody,
            HttpServletRequest httpServletRequest
    ) implements UseCase.InputValue {}
}
