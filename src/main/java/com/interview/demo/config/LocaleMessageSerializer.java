package com.interview.demo.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.demo.constant.enumuration.MessageCode;
import com.interview.demo.domain.entities.api.response.DynamicLocaleMessage;
import com.interview.demo.domain.entities.api.response.FixedLocaleMessage;
import com.interview.demo.domain.entities.api.response.LocaleMessage;


import java.io.IOException;
import java.util.Map;

public class LocaleMessageSerializer extends JsonDeserializer<LocaleMessage> {
    @Override
    public LocaleMessage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        if (node.has("messageCode")) {
            String messageCodeStr = node.get("messageCode").asText();
            MessageCode messageCode = MessageCode.valueOf(messageCodeStr);

            // If there is params -> DynamicLocaleMessage
            if (node.has("params") && !node.get("params").isNull()) {
                JsonNode paramsNode = node.get("params");
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> params = mapper.convertValue(paramsNode,
                        new TypeReference<Map<String, Object>>() {});
                return new DynamicLocaleMessage(messageCode, params);
            }
            // Else -> FixedLocaleMessage
            else {
                return new FixedLocaleMessage(messageCode);
            }
        }

        // Fallback
        return new FixedLocaleMessage();
    }
}
