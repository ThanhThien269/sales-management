package com.interview.demo.infrastructure.persistence.converter;

import com.interview.demo.constant.database.InvoiceStatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class InvoiceStatusConverter implements AttributeConverter<InvoiceStatusEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(InvoiceStatusEnum attribute) {
        return attribute != null ? attribute.getId() : null;
    }

    @Override
    public InvoiceStatusEnum convertToEntityAttribute(Integer dbData) {
        return dbData != null ? InvoiceStatusEnum.fromId(dbData) : null;
    }
}


