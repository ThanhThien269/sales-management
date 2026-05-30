package com.interview.demo.domain.entities.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.interview.demo.common.usecase.UseCase;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse implements UseCase.OutputValue, Serializable {
    private String result;
    private Object content;
    private String message;
    private LocaleMessage localeMessage;
    private HttpStatus status;
    private Object warnings;
    private Object errors;
    @JsonIgnore
    private HttpHeaders headers;

    public ApiResponse(String result, Object content, HttpStatus status) {
        this.result = result;
        this.content = content;
        this.status = status;
    }

    public ApiResponse(String result, Object content, String message) {
        this.result = result;
        this.content = content;
        this.message = message;
    }

    public ApiResponse(String result, Object content) {
        this.result = result;
        this.content = content;
    }

    @Override
    public String toString() {
        return "{" +
                "result='" + result + '\'' +
                ", content=" + content +
                ", message='" + message + '\'' +
                ", localeMessage=" + localeMessage +
                ", status=" + status +
                ", errors=" + errors +
                ", warnings=" + warnings +
                ", headers=" + headers +
                '}';
    }
}
