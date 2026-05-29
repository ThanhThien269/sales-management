package com.interview.demo.domain.entities.api.response;


import com.interview.demo.constant.enumuration.MessageCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
    public class DynamicLocaleMessage extends LocaleMessage {
    private Map<String, Object> params;

    public DynamicLocaleMessage(MessageCode messageCode, Map<String, Object> params) {
        super(messageCode);
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DynamicLocaleMessage that = (DynamicLocaleMessage) o;
        return Objects.equals(params, that.params) && Objects.equals(super.getMessageCode(), that.getMessageCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getMessageCode(), params);
    }
}
