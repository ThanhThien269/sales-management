package com.interview.demo.infrastructure.persistence.converter;

import com.interview.demo.constant.database.ProductStatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ProductStatusConverter implements AttributeConverter<ProductStatusEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProductStatusEnum attribute) {
        return attribute != null ? attribute.getId() : null;
    }

    @Override
    public ProductStatusEnum convertToEntityAttribute(Integer dbData) {
        return dbData != null ? ProductStatusEnum.fromId(dbData) : null;
    }
}


