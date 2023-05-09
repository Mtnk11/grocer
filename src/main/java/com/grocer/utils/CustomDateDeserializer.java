package com.grocer.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String localDate = jsonParser.getValueAsString();

        if(localDate != null) {
            if (localDate.contains(" ")) {
                return parseDate(localDate.replace(" ", "T")+".000Z");
            }
            return parseDate(localDate);
        }
        throw new IllegalArgumentException("Unrecognized date ");
    }
    public static LocalDateTime parseDate(String startDate) {
        if (startDate == null || startDate.isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz"));
    }

}
