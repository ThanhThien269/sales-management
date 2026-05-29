package com.interview.demo.core.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import java.io.IOException;
import java.util.Optional;

public class OptionalDeserializer extends JsonDeserializer<Optional<?>> implements ContextualDeserializer {
    private final JavaType valueType;

    public OptionalDeserializer() {
        this.valueType = null;
    }

    public OptionalDeserializer(JavaType valueType) {
        this.valueType = valueType;
    }

    @Override
    public Optional<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.getCurrentToken() == JsonToken.VALUE_NULL || p.getCurrentToken() == JsonToken.NOT_AVAILABLE) {
            return Optional.empty();
        }

        Object value = ctxt.readValue(p, valueType);
        return Optional.ofNullable(value);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
        if (property == null) {
            throw new IllegalArgumentException("BeanProperty is null");
        }
        JavaType wrapperType = property.getType();
        JavaType valueType = wrapperType.containedType(0);
        return new OptionalDeserializer(valueType);
    }

    @Override
    public Optional<?> getNullValue(DeserializationContext ctxt) {
        return Optional.empty();
    }

    @Override
    public Optional<?> getAbsentValue(DeserializationContext ctxt) {
        return Optional.empty();
    }
}