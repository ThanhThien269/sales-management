package com.interview.demo.core.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class DateDeserializer extends JsonDeserializer<Optional<Date>> {
    @Override
    public Optional<Date> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // check null
        if (p.getCurrentToken() == JsonToken.VALUE_NULL) {
            return Optional.empty();
        }

        //handle  timestamp
        if (p.getCurrentToken() == JsonToken.VALUE_NUMBER_INT) {
            long timestamp = p.getLongValue();
            return Optional.of(new Date(timestamp));
        }

        // Handles date strings
        if (p.getCurrentToken() == JsonToken.VALUE_STRING) {
            String dateStr = p.getText();
            if (dateStr == null || dateStr.isEmpty()) {
                return Optional.empty();
            }

            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                return Optional.of(format.parse(dateStr));
            } catch (ParseException e) {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }
}