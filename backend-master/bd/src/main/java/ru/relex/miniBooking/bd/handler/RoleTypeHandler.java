package ru.relex.miniBooking.bd.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import ru.relex.miniBooking.commons.model.Role;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleTypeHandler implements TypeHandler<Role> {

    @Override
    public void setParameter ( PreparedStatement ps, int i, Role parameter, JdbcType jdbcType ) throws SQLException {
        if ( parameter == null ) {
            ps.setNull ( i, jdbcType.TYPE_CODE );
        } else {
            ps.setInt ( i, parameter.getId ( ) );
        }

    }

    @Override
    public Role getResult ( ResultSet rs, String columnName ) throws SQLException {
        return Role.fromId ( rs.getInt ( columnName ) );
    }

    @Override
    public Role getResult ( ResultSet rs, int columnIndex ) throws SQLException {
        return Role.fromId ( rs.getInt ( columnIndex ) );
    }

    @Override
    public Role getResult ( CallableStatement cs, int columnIndex ) throws SQLException {
        return Role.fromId ( cs.getInt ( columnIndex ) );
    }
}
