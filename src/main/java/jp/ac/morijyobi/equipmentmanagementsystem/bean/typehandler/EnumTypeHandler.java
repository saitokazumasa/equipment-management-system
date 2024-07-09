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
    public void setNonNullParameter(final PreparedStatement ps, final int i, final T parameter, final JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.toString(), Types.OTHER);
    }

    @Override
    public T getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        final String enumName = rs.getString(columnName);
        return toEnum(enumName);
    }

    @Override
    public T getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
        final String enumName = rs.getString(columnIndex);
        return toEnum(enumName);
    }

    @Override
    public T getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        final String enumName = cs.getString(columnIndex);
        return toEnum(enumName);
    }

    private T toEnum(final String name) {
        if (name == null || name.isEmpty()) return null;

        return Enum.valueOf(enumType, name);
    }
}
