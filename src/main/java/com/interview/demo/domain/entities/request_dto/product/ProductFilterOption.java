package com.interview.demo.domain.entities.request_dto.product;

import com.interview.demo.core.filter.FilterOption;
import com.interview.demo.constant.database.ProductStatusEnum;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterOption extends FilterOption {


    private String name;
    private Integer categoryId;
    private UUID productId;
    private Integer statusId;

    @Override
    public boolean isAllFieldsNull() {
        return name == null
                && categoryId == null
                && productId == null
                && statusId == null;
    }
    public ProductStatusEnum getStatusEnum() {
        return statusId != null ? ProductStatusEnum.fromId(statusId) : null;
    }
}

