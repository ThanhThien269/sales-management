package com.interview.demo.application.controller;

import com.interview.demo.application.usecase.invoice.CreateInvoiceUseCase;
import com.interview.demo.application.usecase.invoice.FilterInvoiceUseCase;
import com.interview.demo.common.usecase.UseCaseExecutor;
import com.interview.demo.domain.entities.api.request.FilterRequest;
import com.interview.demo.domain.entities.api.response.ApiResponse;
import com.interview.demo.domain.entities.api.response.ResponseMapper;
import com.interview.demo.domain.entities.request_dto.invoice.CreateInvoiceRequestBody;
import com.interview.demo.domain.entities.request_dto.invoice.InvoiceFilterOption;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Tag(name = "Invoice", description = "Operations related to invoices")
@RestController
@RequestMapping(consumes = {"*/*"}, produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class InvoiceController {

    final UseCaseExecutor useCaseExecutor;
    final CreateInvoiceUseCase createInvoiceUseCase;
    final FilterInvoiceUseCase filterInvoiceUseCase;

    @Operation(summary = "Create a new invoice")
    @PostMapping(value = "/invoice")
    public CompletableFuture<ResponseEntity<ApiResponse>> createInvoice(
            @RequestBody @Validated CreateInvoiceRequestBody requestBody,
            HttpServletRequest httpServletRequest
    ) {
        return useCaseExecutor.execute(
                createInvoiceUseCase,
                new CreateInvoiceUseCase.InputValue(requestBody, httpServletRequest),
                null,
                ResponseMapper::map
        );
    }

    @Operation(summary = "Filter invoices with pagination")
    @PostMapping(value = "/invoice/filter")
    public CompletableFuture<ResponseEntity<ApiResponse>> filterInvoices(
            @RequestBody FilterRequest<InvoiceFilterOption> filterRequest,
            HttpServletResponse httpServletResponse
    ) {
        return useCaseExecutor.execute(
                filterInvoiceUseCase,
                new FilterInvoiceUseCase.InputValue(filterRequest, httpServletResponse),
                null,
                ResponseMapper::map
        );
    }
}

