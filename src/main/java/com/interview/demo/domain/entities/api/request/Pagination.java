package com.interview.demo.domain.entities.api.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagination implements Serializable {
    @Min(1)
    int page;

    @Min(1)
    @Max(150)
    int size;
}
