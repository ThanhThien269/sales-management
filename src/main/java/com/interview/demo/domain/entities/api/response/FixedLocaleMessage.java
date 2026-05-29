package com.interview.demo.domain.entities.api.response;


import com.interview.demo.constant.enumuration.MessageCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FixedLocaleMessage extends LocaleMessage {
    public FixedLocaleMessage(MessageCode messageCode) {
        super(messageCode);
    }
}
