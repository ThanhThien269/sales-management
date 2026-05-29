package com.interview.demo.application.usecase.product;

import com.interview.demo.application.validation.ProductValidation;
import com.interview.demo.constant.enumuration.MessageCode;
import com.interview.demo.core.usecase.UseCase;
import com.interview.demo.core.usecase.UseCaseContext;
import com.interview.demo.domain.entities.api.response.ApiResponse;
import com.interview.demo.domain.entities.api.response.FixedLocaleMessage;
import com.interview.demo.domain.entities.database.Product;
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
public class DeleteProductUseCase extends UseCase<DeleteProductUseCase.InputValue, ApiResponse, UseCaseContext> {

    private final ProductRepository productRepository;
    private final ProductValidation productValidation;

    @Override
    public ApiResponse execute(InputValue input, UseCaseContext context) {
        UUID productId = input.productId();

        Product product = productValidation.validateProductThenReturn(productId);

        productRepository.delete(product);

        return ApiResponse.builder()
                .result("success")
                .localeMessage(new FixedLocaleMessage(MessageCode.DELETE_SUCCESSFULLY))
                .status(HttpStatus.OK)
                .build();
    }

    public record InputValue(
            UUID productId,
            HttpServletRequest httpServletRequest
    ) implements UseCase.InputValue {}
}

