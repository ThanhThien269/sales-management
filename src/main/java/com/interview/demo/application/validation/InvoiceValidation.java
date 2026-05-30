package com.interview.demo.application.validation;

import com.interview.demo.common.validator.exception.DataNotFoundException;
import com.interview.demo.domain.entities.database.Invoice;
import com.interview.demo.domain.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InvoiceValidation {

    private final InvoiceRepository invoiceRepository;

    public Invoice validateInvoiceThenReturn(UUID id) {
        Invoice invoice = invoiceRepository.findById(id);
        if (invoice == null)
            throw new DataNotFoundException(
                    String.format("Invoice with ID %s not found", id)
            );
        return invoice;
    }
}

