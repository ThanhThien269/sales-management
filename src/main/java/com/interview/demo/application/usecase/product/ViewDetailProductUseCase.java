package com.interview.demo.application.usecase.product;

import com.interview.demo.application.validation.ProductValidation;
import com.interview.demo.common.usecase.UseCase;
import com.interview.demo.common.usecase.UseCaseContext;
import com.interview.demo.domain.entities.api.response.ApiResponse;
import com.interview.demo.domain.entities.database.Product;
import com.interview.demo.domain.entities.response_dto.product.ProductResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Transactional
@Component
@RequiredArgsConstructor
public class ViewDetailProductUseCase extends UseCase<ViewDetailProductUseCase.InputValue, ApiResponse, UseCaseContext> {

    private final ProductValidation productValidation;

    @Override
    public ApiResponse execute(InputValue input, UseCaseContext context) {
        UUID productId = input.productId();


        Product product = productValidation.validateProductThenReturn(productId);


        ProductResponse response = ProductResponse.builder()
                .id(product.getId().toString())
                .categoryId(product.getCategoryId())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .stockQuantity(product.getQuantity())
                .statusId(product.getStatusId())
                .statusName(product.getStatus() != null ? product.getStatus().name() : null)
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();

        return ApiResponse.builder()
                .result("success")
                .content(response)
                .status(HttpStatus.OK)
                .build();
    }

    public record InputValue(
            UUID productId,
            HttpServletRequest httpServletRequest
    ) implements UseCase.InputValue {}
}



