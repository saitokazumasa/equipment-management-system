package jp.ac.morijyobi.equipmentmanagementsystem.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtil<T> {
    private final ObjectMapper _objectMapper;
    private final Class<T> _javaType;

    public JsonUtil(final Class<T> javaType) {
        this._javaType = javaType;

        _objectMapper = new ObjectMapper();
        _objectMapper.registerModule(new JavaTimeModule());
    }

    public T tryToObject(final Object json) {
        if (json == null) return null;

        try {
            return _objectMapper.readValue(json.toString(), _javaType);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String tryToString(final T object) {
        try {
            return _objectMapper.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
