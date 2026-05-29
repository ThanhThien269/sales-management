package com.interview.demo.application.controller;

import com.interview.demo.application.usecase.product.CreateProductUseCase;
import com.interview.demo.application.usecase.product.DeleteProductUseCase;
import com.interview.demo.application.usecase.product.FilterProductUseCase;
import com.interview.demo.application.usecase.product.UpdateProductUseCase;
import com.interview.demo.application.usecase.product.ViewDetailProductUseCase;
import com.interview.demo.core.usecase.UseCaseExecutor;
import com.interview.demo.domain.entities.api.request.FilterRequest;
import com.interview.demo.domain.entities.api.response.ApiResponse;
import com.interview.demo.domain.entities.api.response.ResponseMapper;
import com.interview.demo.domain.entities.request_dto.product.CreateProductRequestBody;
import com.interview.demo.domain.entities.request_dto.product.ProductFilterOption;
import com.interview.demo.domain.entities.request_dto.product.UpdateProductRequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Validated
@Tag(name = "Product", description = "Operations related to products in the system")
@RestController
@RequestMapping(consumes = {"*/*"}, produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class ProductController {
    final UseCaseExecutor useCaseExecutor;
    final CreateProductUseCase createProductUseCase;
    final UpdateProductUseCase updateProductUseCase;
    final DeleteProductUseCase deleteProductUseCase;
    final ViewDetailProductUseCase viewDetailProductUseCase;
    final FilterProductUseCase filterProductUseCase;

    @Operation(summary = "Add a new product")
    @PostMapping(value = "/product")
    public CompletableFuture<ResponseEntity<ApiResponse>> addProduct(
            @RequestBody @Validated CreateProductRequestBody requestBody,
            HttpServletRequest httpServletRequest
    ) {
        return useCaseExecutor.execute(
                createProductUseCase,
                new CreateProductUseCase.InputValue(requestBody, httpServletRequest),
                null,
                ResponseMapper::map
        );
    }

    @Operation(summary = "Update an existing product")
    @PatchMapping(value = "/product/{id}")
    public CompletableFuture<ResponseEntity<ApiResponse>> updateProduct(
            @PathVariable UUID id,
            @RequestBody @Validated UpdateProductRequestBody requestBody,
            HttpServletRequest httpServletRequest
    ) {
        return useCaseExecutor.execute(
                updateProductUseCase,
                new UpdateProductUseCase.InputValue(id, requestBody, httpServletRequest),
                null,
                ResponseMapper::map
        );
    }

    @Operation(summary = "Delete a product by ID")
    @DeleteMapping(value = "/product/{id}")
    public CompletableFuture<ResponseEntity<ApiResponse>> deleteProduct(
            @PathVariable UUID id,
            HttpServletRequest httpServletRequest
    ) {
        return useCaseExecutor.execute(
                deleteProductUseCase,
                new DeleteProductUseCase.InputValue(id, httpServletRequest),
                null,
                ResponseMapper::map
        );
    }

    @Operation(summary = "Get product detail by ID")
    @GetMapping(value = "/product/{id}")
    public CompletableFuture<ResponseEntity<ApiResponse>> getProductDetail(
            @PathVariable UUID id,
            HttpServletRequest httpServletRequest
    ) {
        return useCaseExecutor.execute(
                viewDetailProductUseCase,
                new ViewDetailProductUseCase.InputValue(id, httpServletRequest),
                null,
                ResponseMapper::map
        );
    }

    @Operation(summary = "Filter products with pagination")
    @PostMapping(value = "/product/filter")
    public CompletableFuture<ResponseEntity<ApiResponse>> filterProducts(
            @RequestBody FilterRequest<ProductFilterOption> filterRequest,
            HttpServletResponse httpServletResponse
    ) {
        return useCaseExecutor.execute(
                filterProductUseCase,
                new FilterProductUseCase.InputValue(filterRequest, httpServletResponse),
                null,
                ResponseMapper::map
        );
    }
}
