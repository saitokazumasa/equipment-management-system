package jp.ac.morijyobi.equipmentmanagementsystem.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EditLog;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Sample;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedTypes({List.class})
public class JsonTypeHandler<T> extends BaseTypeHandler<T> {

    private final ObjectMapper objectMapper;
    private final TypeReference<T> typeReference;

    public JsonTypeHandler(final TypeReference<T> typeReference) {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        this.typeReference = typeReference;
    }

    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int index, final T parameter, final JdbcType jdbcType) throws SQLException {
        var json = tryToString(parameter);
        ps.setString(index, json);
    }

    @Override
    public T getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        var json = rs.getString(columnName);
        return tryToClass(json);
    }

    @Override
    public T getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
        var json = rs.getString(columnIndex);
        return tryToClass(json);
    }

    @Override
    public T getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        var json = cs.getString(columnIndex);
        return tryToClass(json);
    }

    private T tryToClass(final String json) {
        try {
            return objectMapper.readValue(json, typeReference);
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
