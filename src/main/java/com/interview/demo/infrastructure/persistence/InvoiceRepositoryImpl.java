package com.interview.demo.infrastructure.persistence;

import com.interview.demo.domain.entities.database.Invoice;
import com.interview.demo.domain.entities.database.InvoiceItem;
import com.interview.demo.domain.entities.request_dto.invoice.InvoiceFilterOption;
import com.interview.demo.domain.entities.response_dto.invoice.InvoiceItemResponse;
import com.interview.demo.domain.entities.response_dto.invoice.InvoiceResponse;
import com.interview.demo.domain.repositories.InvoiceRepository;
import com.interview.demo.infrastructure.persistence.jpa.InvoiceItemJpaRepository;
import com.interview.demo.infrastructure.persistence.jpa.InvoiceJpaRepository;
import com.interview.demo.infrastructure.persistence.mybatis.InvoiceMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final InvoiceJpaRepository jpaRepository;
    private final InvoiceItemJpaRepository itemJpaRepository;
    private final InvoiceMybatisRepository myBatisRepository;

    @Override public Invoice save(Invoice invoice) { return jpaRepository.save(invoice); }
    @Override public List<Invoice> saveAll(Iterable<Invoice> invoices) { return jpaRepository.saveAll(invoices); }
    @Override public Invoice findById(UUID id) { return jpaRepository.findById(id).orElse(null); }
    @Override public List<Invoice> findByIds(Iterable<UUID> ids) { return jpaRepository.findAllById(ids); }
    @Override public List<Invoice> findAll() { return jpaRepository.findAll(); }
    @Override public void deleteById(UUID id) { jpaRepository.deleteById(id); }
    @Override public void delete(Invoice invoice) { jpaRepository.delete(invoice); }
    @Override public void deleteByIds(Iterable<UUID> ids) { jpaRepository.deleteAllById(ids); }
    @Override public boolean existsById(UUID id) { return jpaRepository.existsById(id); }
    @Override public void flush() { jpaRepository.flush(); }

    @Override
    public List<InvoiceResponse> findByFilterOptions(InvoiceFilterOption filter, Integer page, Integer size) {
        return myBatisRepository.findByFilterOptions(filter, page, size);
    }

    @Override
    public List<InvoiceItem> saveAllItems(List<InvoiceItem> items) {
        return itemJpaRepository.saveAll(items);
    }

    @Override
    public List<InvoiceItemResponse> findItemsByInvoiceIds(List<String> invoiceIds) {
        if (invoiceIds == null || invoiceIds.isEmpty()) return Collections.emptyList();
        return myBatisRepository.findItemsByInvoiceIds(invoiceIds);
    }
}

