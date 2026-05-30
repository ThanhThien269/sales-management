package com.interview.demo.domain.entities.request_dto.product;

import com.interview.demo.core.filter.FilterOption;
import lombok.*;

import java.util.List;
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
    private List<Integer> status;

    @Override
    public boolean isAllFieldsNull() {
        return name == null
                && categoryId == null
                && productId == null
                && (status == null || status.isEmpty());
    }
}

