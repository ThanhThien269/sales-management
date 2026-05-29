package com.interview.demo.domain.entities.request_dto.invoice;

import com.interview.demo.core.validator.uuid.UuidValid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemRequest {

    @NotNull
    @UuidValid
    private UUID productId;

    @NotNull
    @Min(1)
    private Integer quantity;
}

