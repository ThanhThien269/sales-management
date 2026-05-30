package com.interview.demo.application.usecase.product;

import com.interview.demo.application.validation.ProductValidation;
import com.interview.demo.constant.database.ProductStatusEnum;
import com.interview.demo.constant.enumuration.MessageCode;
import com.interview.demo.common.usecase.UseCase;
import com.interview.demo.common.usecase.UseCaseContext;
import com.interview.demo.domain.entities.api.response.ApiResponse;
import com.interview.demo.domain.entities.api.response.FixedLocaleMessage;
import com.interview.demo.domain.entities.database.Product;
import com.interview.demo.domain.entities.request_dto.product.CreateProductRequestBody;
import com.interview.demo.domain.entities.response_dto.product.ProductResponse;
import com.interview.demo.domain.repositories.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Transactional
@Component
@RequiredArgsConstructor
public class CreateProductUseCase extends UseCase<CreateProductUseCase.InputValue, ApiResponse, UseCaseContext> {

    private final ProductRepository productRepository;
    private final ProductValidation productValidation;

    @Override
    public ApiResponse execute(InputValue input, UseCaseContext context) {
        CreateProductRequestBody requestBody = input.requestBody();


        productValidation.validateCategory(requestBody.getCategoryId());

        Product product = Product.builder()
                .categoryId(requestBody.getCategoryId())
                .name(requestBody.getName())
                .description(requestBody.getDescription())
                .price(requestBody.getPrice())
                .quantity(requestBody.getStockQuantity())
                .soldQuantity(0)
                .image(requestBody.getImageUrl())
                .status(ProductStatusEnum.ACTIVE)
                .build();

        Product saved = productRepository.save(product);

        ProductResponse response = ProductResponse.builder()
                .id(saved.getId().toString())
                .categoryId(saved.getCategoryId())
                .name(saved.getName())
                .description(saved.getDescription())
                .image(saved.getImage())
                .price(saved.getPrice())
                .stockQuantity(saved.getQuantity())
                .statusId(saved.getStatusId())
                .statusName(saved.getStatus().name())
                .createdAt(saved.getCreatedAt())
                .updatedAt(saved.getUpdatedAt())
                .build();

        return ApiResponse.builder()
                .result("success")
                .localeMessage(new FixedLocaleMessage(MessageCode.CREATE_SUCCESSFULLY))
                .content(response)
                .status(HttpStatus.CREATED)
                .build();
    }

    public record InputValue(
            CreateProductRequestBody requestBody,
            HttpServletRequest httpServletRequest
    ) implements UseCase.InputValue {}
}
