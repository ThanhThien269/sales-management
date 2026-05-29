package com.interview.demo.domain.entities.database;

import com.interview.demo.constant.database.InvoiceStatusEnum;
import com.interview.demo.domain.abstracts.DbEntity;
import com.interview.demo.infrastructure.persistence.converter.InvoiceStatusConverter;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice extends DbEntity {

    @Id
    private UUID id;

    @Column(name = "invoice_number", unique = true, nullable = false, length = 10)
    private String invoiceNumber;

    @Column(name = "invoice_note")
    private String invoiceNote;

    @Column(name = "customer_name", length = 100)
    private String customerName;

    @Column(name = "customer_phone", length = 20)
    private String customerPhone;

    @Column(name = "total_amount", precision = 20, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "extra_fee", precision = 20, scale = 2)
    private BigDecimal extraFee;

    @Column(name = "status_id", nullable = false)
    @Convert(converter = InvoiceStatusConverter.class)
    private InvoiceStatusEnum status;

    @Column(name = "discount_amount", precision = 20, scale = 2)
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "discount_percentage")
    private BigDecimal discountPercentage = BigDecimal.ZERO;

    @Column(name = "original_amount", precision = 20, scale = 2)
    private BigDecimal originalAmount;

    @Transient
    public Integer getStatusId() {
        return status != null ? status.getId() : null;
    }

    @PrePersist
    public void prePersist() {
        if (this.id == null) this.id = UUID.randomUUID();
    }
}
