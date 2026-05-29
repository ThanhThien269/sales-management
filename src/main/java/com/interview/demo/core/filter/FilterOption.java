package com.interview.demo.core.filter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@NoArgsConstructor
@AllArgsConstructor
public abstract class FilterOption {
    String type;

    public abstract boolean isAllFieldsNull();
}
