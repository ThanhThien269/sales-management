package com.interview.demo.constant.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ProductStatusEnum {
    ACTIVE (1),
    INACTIVE(2),
    OUT_OF_STOCK(3);

    private final int id;

    public static ProductStatusEnum fromId(int id) {
        return Arrays.stream(values())
                .filter(status -> status.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status ID: " + id));
    }
}
