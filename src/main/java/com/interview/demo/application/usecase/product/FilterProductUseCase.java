package com.interview.demo.application.usecase.product;

import com.interview.demo.common.usecase.UseCase;
import com.interview.demo.common.usecase.UseCaseContext;
import com.interview.demo.domain.entities.api.request.FilterRequest;
import com.interview.demo.domain.entities.api.request.Pagination;
import com.interview.demo.domain.entities.api.response.ApiResponse;
import com.interview.demo.domain.entities.request_dto.product.ProductFilterOption;
import com.interview.demo.domain.entities.response_dto.product.ProductResponse;
import com.interview.demo.domain.repositories.ProductRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FilterProductUseCase extends UseCase<FilterProductUseCase.InputValue, ApiResponse, UseCaseContext> {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final ProductRepository productRepository;

    @Override
    public ApiResponse execute(InputValue input, UseCaseContext context) {
        FilterRequest<ProductFilterOption> filterRequest = input.request();


        if (filterRequest.getPagination() == null) {
            filterRequest.setPagination(
                    Pagination.builder()
                            .page(1)
                            .size(DEFAULT_PAGE_SIZE)
                            .build()
            );
        }


        if (filterRequest.getFilterOption() == null) {
            filterRequest.setFilterOption(new ProductFilterOption());
        }

        ProductFilterOption filterOption = filterRequest.getFilterOption();
        Pagination pagination = filterRequest.getPagination();


        List<ProductResponse> products = productRepository.findByFilterOptions(
                filterOption,
                pagination.getPage(),
                pagination.getSize()
        );

        if (products == null) products = List.of();


        input.httpServletResponse().setHeader(
                "X-Total",
                products.isEmpty() ? "0" : products.get(0).getTotal().toString()
        );

        return ApiResponse.builder()
                .result("success")
                .content(products)
                .status(HttpStatus.OK)
                .build();
    }

    public record InputValue(
            FilterRequest<ProductFilterOption> request,
            HttpServletResponse httpServletResponse
    ) implements UseCase.InputValue {}
}




