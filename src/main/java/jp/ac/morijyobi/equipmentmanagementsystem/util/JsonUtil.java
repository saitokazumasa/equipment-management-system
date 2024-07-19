package jp.ac.morijyobi.equipmentmanagementsystem.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtil {
    private static final ObjectMapper objectMapper =
            new ObjectMapper()
                    .registerModule(new JavaTimeModule());
    
    private JsonUtil() {
    }

    public static <T> T tryToObject(final Object json, final Class<T> javaType) {
        if (json == null) return null;

        try {
            return objectMapper.readValue(json.toString(), javaType);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String tryToString(final T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
