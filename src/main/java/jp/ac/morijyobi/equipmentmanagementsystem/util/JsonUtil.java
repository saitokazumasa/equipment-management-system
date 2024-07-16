package jp.ac.morijyobi.equipmentmanagementsystem.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtil<T> {
    private final ObjectMapper objectMapper;
    private final Class<T> javaType;

    public JsonUtil(final Class<T> javaType) {
        this.javaType = javaType;

        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public T tryToObject(final Object json) {
        if (json == null) return null;

        try {
            return this.objectMapper.readValue(json.toString(), javaType);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String tryToString(final T object) {
        try {
            return this.objectMapper.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
