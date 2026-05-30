package com.interview.demo.application.controller;

import com.interview.demo.application.usecase.GetListCategoryUseCase;
import com.interview.demo.common.usecase.UseCaseExecutor;
import com.interview.demo.domain.entities.api.response.ApiResponse;
import com.interview.demo.domain.entities.api.response.ResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Tag(name = "Category", description = "Operations related to product categories")
@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class CategoryController {

    final UseCaseExecutor useCaseExecutor;
    final GetListCategoryUseCase getListCategoryUseCase;

    @Operation(summary = "Get all categories")
    @GetMapping(value = "/category")
    public CompletableFuture<ResponseEntity<ApiResponse>> getListCategory(
            HttpServletRequest httpServletRequest
    ) {
        return useCaseExecutor.execute(
                getListCategoryUseCase,
                new GetListCategoryUseCase.InputValue(httpServletRequest),
                null,
                ResponseMapper::map
        );
    }
}

