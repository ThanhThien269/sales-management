package com.interview.demo.infrastructure.persistence.jpa;

import com.interview.demo.domain.entities.database.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceJpaRepository extends JpaRepository<Invoice, UUID> {
}

