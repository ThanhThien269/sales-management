package com.interview.demo.infrastructure.persistence.mybatis;

import com.interview.demo.domain.entities.request_dto.invoice.InvoiceFilterOption;
import com.interview.demo.domain.entities.response_dto.invoice.InvoiceItemResponse;
import com.interview.demo.domain.entities.response_dto.invoice.InvoiceResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoiceMybatisRepository {
    List<InvoiceResponse> findByFilterOptions(
            @Param("f") InvoiceFilterOption filter,
            @Param("page") Integer page,
            @Param("size") Integer size
    );

    List<InvoiceItemResponse> findItemsByInvoiceIds(
            @Param("invoiceIds") List<String> invoiceIds
    );
}

