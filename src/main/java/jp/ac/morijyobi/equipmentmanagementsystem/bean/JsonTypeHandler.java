package jp.ac.morijyobi.equipmentmanagementsystem.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.util.List;

@MappedTypes({List.class})
public class JsonTypeHandler<T> extends BaseTypeHandler<T> {

    private final ObjectMapper objectMapper;
    private final Class<T> javaType;

    public JsonTypeHandler(final Class<T> javaType) {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        this.javaType = javaType;
    }

    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final T parameter, final JdbcType jdbcType) throws SQLException {
        var json = tryToString(parameter);
        ps.setObject(i, json, Types.OTHER);
    }

    @Override
    public T getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        var json = rs.getObject(columnName);
        return tryToObject(json);
    }

    @Override
    public T getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
        var json = rs.getObject(columnIndex);
        return tryToObject(json);
    }

    @Override
    public T getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        var json = cs.getObject(columnIndex);
        return tryToObject(json);
    }

    private T tryToObject(final Object json) {
        if (json == null) return null;

        try {
            return objectMapper.readValue(json.toString(), javaType);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String tryToString(final T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
