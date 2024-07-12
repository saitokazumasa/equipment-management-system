package jp.ac.morijyobi.equipmentmanagementsystem.bean.typehandler;

import jp.ac.morijyobi.equipmentmanagementsystem.util.JsonUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

public class JsonTypeHandler<T> extends BaseTypeHandler<T> {
    private final JsonUtil<T> jsonUtil;

    public JsonTypeHandler(final Class<T> javaType) {
        this.jsonUtil = new JsonUtil<>(javaType);
    }

    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final T parameter, final JdbcType jdbcType) throws SQLException {
        final String json = jsonUtil.tryToString(parameter);
        ps.setObject(i, json, Types.OTHER);
    }

    @Override
    public T getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        final Object json = rs.getObject(columnName);
        return jsonUtil.tryToObject(json);
    }

    @Override
    public T getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
        final Object json = rs.getObject(columnIndex);
        return jsonUtil.tryToObject(json);
    }

    @Override
    public T getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        final Object json = cs.getObject(columnIndex);
        return jsonUtil.tryToObject(json);
    }
}
