package com.interview.demo.domain.entities.api.request;


import com.interview.demo.constant.enumuration.SortDirection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;


import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterSort implements Serializable {
    @Enumerated(EnumType.STRING)
    SortDirection type;

    String key;
}
