package com.interview.demo.infrastructure.persistence.jpa;

import com.interview.demo.domain.entities.database.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InvoiceItemJpaRepository extends JpaRepository<InvoiceItem, UUID> {
    List<InvoiceItem> findByInvoiceIdIn(List<UUID> invoiceIds);
}

