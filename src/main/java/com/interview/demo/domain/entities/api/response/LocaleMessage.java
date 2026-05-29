package com.interview.demo.domain.entities.api.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.interview.demo.config.LocaleMessageSerializer;
import com.interview.demo.constant.enumuration.MessageCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = LocaleMessageSerializer.class)
public abstract class LocaleMessage implements Serializable {
    private MessageCode messageCode;
}
