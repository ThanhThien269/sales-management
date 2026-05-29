package com.interview.demo.core.validator.exception;

import com.interview.demo.domain.entities.api.response.LocaleMessage;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataNotFoundException extends RuntimeException {
    private LocaleMessage localeMessage;
    private String debugMessage;

    public DataNotFoundException(String message) {
        super(message);
        this.debugMessage = message;
    }

    public DataNotFoundException(LocaleMessage localeMessage) {
        this.localeMessage = localeMessage;
    }

    public DataNotFoundException(String message, LocaleMessage localeMessage) {
        super(message);
        this.debugMessage = message;
        this.localeMessage = localeMessage;
    }
}
