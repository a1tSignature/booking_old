package ru.relex.miniBooking.bd.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.miniBooking.bd.handler.RoleTypeHandler;
import ru.relex.miniBooking.bd.model.UserModel;
import ru.relex.miniBooking.commons.model.Role;

@Mapper
public interface UserMapper {

    UserModel createUser ( UserModel user );

    //language=postgreSQL
    @Select(" SELECT users.user_id AS id, " +
            "       username," +
            "       first_name," +
            "       last_name," +
            "       created_at," +
            "       is_locked AS locked," +
            "       is_active AS active," +
            "       phone," +
            "       email," +
            "       role_id AS role, " +
            "       country, " +
            "       city, " +
            "       street," +
            "       house " +
            "FROM users " +
            "INNER JOIN user_roles ON users.user_id = user_roles.user_id " +
            "WHERE users.user_id = #{id}"
    )
    @Result(column = "role", property = "role",
            typeHandler = RoleTypeHandler.class)
    UserModel findById ( @Param("id") long id );

    void updateUser ( UserModel user );

    void saveUserRole ( @Param("userId") long userId, @Param("role") Role role );

    @Delete ( "DELETE FROM users USING user_roles WHERE users.user_id=user_roles.user_id AND users.user_id=#{id}" )
    boolean deleteUser ( long id );
}
