package com.interview.demo.domain.entities.response_dto.product;

import com.interview.demo.domain.entities.api.response.FilterResult;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse extends FilterResult {
    private String id;
    private Integer categoryId;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private Integer stockQuantity;
    private String statusName;
    private Integer statusId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
