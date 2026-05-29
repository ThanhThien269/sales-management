package com.interview.demo.domain.entities.response_dto.invoice;

import com.interview.demo.domain.entities.api.response.FilterResult;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponse extends FilterResult {
    private String id;
    private String invoiceNumber;
    private String invoiceNote;
    private String customerName;
    private String customerPhone;
    private List<InvoiceItemResponse> items;
    private BigDecimal originalAmount;
    private BigDecimal extraFee;
    private BigDecimal discountAmount;
    private BigDecimal discountPercentage;
    private BigDecimal totalAmount;
    private Integer statusId;
    private String statusName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
