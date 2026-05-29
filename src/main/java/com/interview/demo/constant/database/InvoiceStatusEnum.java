package com.interview.demo.constant.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum InvoiceStatusEnum {
    WAITING(1),
    PAID(2),
    CANCELED(3);

    private final int id;

    public static InvoiceStatusEnum fromId(int id) {
        return Arrays.stream(values())
                .filter(status -> status.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status ID: " + id));
    }
}