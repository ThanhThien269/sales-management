package com.interview.demo.domain.entities.api.request;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.interview.demo.core.filter.FilterOption;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Validated
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterRequest<T extends FilterOption> extends ApiRequest implements Serializable {
    @Valid
    T filterOption;

    @Valid
    Pagination pagination;

    List<FilterSort> sorts;

    @Override
    public Map<String, Object> toMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(this, new TypeReference<>() {});
    }
}
