package com.interview.demo.domain.entities.request_dto.invoice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.demo.core.validator.uuid.UuidValid;
import com.interview.demo.domain.entities.api.request.ApiRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequestBody extends ApiRequest {

    @UuidValid
    private UUID productId;

    @NotNull
    @Min(1)
    private Integer quantity;

    private String note;

    @NotBlank
    private String customerName;

    @NotBlank
    private String customerPhoneNumber;

    private BigDecimal extraFee;

    private BigDecimal originalAmount;

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

    @Override
    public String toString() {
        return "CreateInvoiceRequestBody{" +
                ", extraFee=" + extraFee +
                ", originalAmount=" + originalAmount +
                ", discountAmount=" + discountAmount +
                ", discountPercentage=" + discountPercentage +
                '}';
    }


}
