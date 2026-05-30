package com.interview.demo.domain.entities.request_dto.invoice;

import com.interview.demo.constant.database.InvoiceStatusEnum;
import com.interview.demo.common.filter.FilterOption;
import lombok.*;

import java.util.Date;
import java.util.List;


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




