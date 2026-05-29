package com.interview.demo.application.usecase.invoice;

import com.interview.demo.core.usecase.UseCase;
import com.interview.demo.core.usecase.UseCaseContext;
import com.interview.demo.domain.entities.api.request.FilterRequest;
import com.interview.demo.domain.entities.api.request.Pagination;
import com.interview.demo.domain.entities.api.response.ApiResponse;
import com.interview.demo.domain.entities.request_dto.invoice.InvoiceFilterOption;
import com.interview.demo.domain.entities.response_dto.invoice.InvoiceResponse;
import com.interview.demo.domain.repositories.InvoiceRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FilterInvoiceUseCase extends UseCase<FilterInvoiceUseCase.InputValue, ApiResponse, UseCaseContext> {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final InvoiceRepository invoiceRepository;

    @Override
    public ApiResponse execute(InputValue input, UseCaseContext context) {
        FilterRequest<InvoiceFilterOption> filterRequest = input.request();

        if (filterRequest.getPagination() == null) {
            filterRequest.setPagination(
                    Pagination.builder()
                            .page(1)
                            .size(DEFAULT_PAGE_SIZE)
                            .build()
            );
        }

        if (filterRequest.getFilterOption() == null) {
            filterRequest.setFilterOption(new InvoiceFilterOption());
        }

        InvoiceFilterOption filterOption = filterRequest.getFilterOption();
        Pagination pagination = filterRequest.getPagination();

        List<InvoiceResponse> invoices = invoiceRepository.findByFilterOptions(
                filterOption,
                pagination.getPage(),
                pagination.getSize()
        );

        if (invoices == null) invoices = List.of();

        input.httpServletResponse().setHeader(
                "X-Total",
                invoices.isEmpty() ? "0" : invoices.get(0).getTotal().toString()
        );

        return ApiResponse.builder()
                .result("success")
                .content(invoices)
                .status(HttpStatus.OK)
                .build();
    }

    public record InputValue(
            FilterRequest<InvoiceFilterOption> request,
            HttpServletResponse httpServletResponse
    ) implements UseCase.InputValue {}
}

