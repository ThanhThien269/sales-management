package com.interview.demo.domain.entities.request_dto.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.demo.domain.entities.api.request.ApiRequest;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequestBody extends ApiRequest {
    @Min(1)
    @NotNull
    private Integer categoryId;

    @NotBlank
    @Size(max = 150)
    private String name;

    private String description;

    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal price;

    @NotNull
    @Min(0)
    private Integer stockQuantity;

    @NotNull
    @Min(0)
    private Integer soldQuantity;

    private String imageUrl;


    @Override
    public Map<String, Object> toMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(this, Map.class);
    }
}

