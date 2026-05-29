package com.interview.demo.domain.entities.response_dto.invoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemResponse {

    @JsonIgnore
    private String invoiceId;

    private String productId;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;
}


