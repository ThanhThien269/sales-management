package com.interview.demo.domain.entities.request_dto.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.demo.domain.entities.api.request.ApiRequest;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductRequestBody extends ApiRequest {

    @Min(1)
    private Integer categoryId;

    @Size(max = 150)
    private String name;

    private String description;

    @DecimalMin(value = "0.00")
    private BigDecimal price;

    @Min(0)
    private Integer stockQuantity;

    @Min(0)
    private Integer soldQuantity;

    private String imageUrl;

    private Integer statusId;

    @Override
    public Map<String, Object> toMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(this, Map.class);
    }
}

