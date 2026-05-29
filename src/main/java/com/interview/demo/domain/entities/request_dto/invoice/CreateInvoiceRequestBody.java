package com.interview.demo.domain.entities.request_dto.invoice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.demo.domain.entities.api.request.ApiRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequestBody extends ApiRequest {


    @Valid
    @NotEmpty
    private List<InvoiceItemRequest> items;

    private String note;


    private String customerName;
    private String customerPhoneNumber;

    private BigDecimal extraFee;

    @DecimalMin(value = "0.00")
    private BigDecimal discountAmount;

    @DecimalMin(value = "0.00")
    @DecimalMax(value = "100.00")
    private BigDecimal discountPercentage;

    @Override
    public Map<String, Object> toMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(this, Map.class);
    }

}
