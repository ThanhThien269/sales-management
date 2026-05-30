package com.interview.demo.domain.repositories;

import com.interview.demo.common.repositories.CommonDbEntityRepository;
import com.interview.demo.domain.entities.database.Invoice;
import com.interview.demo.domain.entities.database.InvoiceItem;
import com.interview.demo.domain.entities.request_dto.invoice.InvoiceFilterOption;
import com.interview.demo.domain.entities.response_dto.invoice.InvoiceItemResponse;
import com.interview.demo.domain.entities.response_dto.invoice.InvoiceResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvoiceRepository extends CommonDbEntityRepository<Invoice, UUID> {
    List<InvoiceResponse> findByFilterOptions(InvoiceFilterOption filter, Integer page, Integer size);
    List<InvoiceItem> saveAllItems(List<InvoiceItem> items);
    List<InvoiceItemResponse> findItemsByInvoiceIds(List<String> invoiceIds);
}

