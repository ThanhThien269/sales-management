package com.interview.demo.domain.entities.request_dto.invoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.interview.demo.constant.database.InvoiceStatusEnum;
import com.interview.demo.core.filter.FilterOption;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceFilterOption extends FilterOption {
    private Date bookingDate;

    private String invoiceNumber;

    private Date createdAt;

    private List<InvoiceStatusEnum> status;


    @Override
    public boolean isAllFieldsNull() {
        return bookingDate == null &&
                invoiceNumber == null &&
                createdAt == null &&
                status == null;
    }
}




