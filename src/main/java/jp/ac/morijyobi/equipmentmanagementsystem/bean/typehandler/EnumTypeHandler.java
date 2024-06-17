package jp.ac.morijyobi.equipmentmanagementsystem.bean.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

public class EnumTypeHandler<T extends Enum<T>> extends BaseTypeHandler<T> {

    private final Class<T> enumType;

    public EnumTypeHandler(final Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.toString(), Types.OTHER);
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        var enumName = rs.getString(columnName);
        return toEnum(enumName);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        var enumName = rs.getString(columnIndex);
        return toEnum(enumName);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        var enumName = cs.getString(columnIndex);
        return toEnum(enumName);
    }

    private T toEnum(final String name) {
        if (name == null || name.isEmpty()) return null;

        return Enum.valueOf(enumType, name);
    }
}
