package ru.relex.miniBooking.bd.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import ru.relex.miniBooking.bd.handler.RoleTypeHandler;
import ru.relex.miniBooking.bd.model.UserModel;
import ru.relex.miniBooking.bd.model.UserProfile;

import java.util.Optional;

@Mapper
public interface UserAuthMapper {
    //language=postgreSQL
    @Select(" SELECT users.user_id AS id, " +
            "       username," +
            "       password," +
            "       first_name," +
            "       last_name," +
            "       is_locked AS locked," +
            "       is_active AS active," +
            "       role_id AS role " +
            "FROM users " +
            "INNER JOIN user_roles ON users.user_id = user_roles.user_id " +
            "WHERE LOWER(username)=LOWER(#{searchString})" +
            "OR LOWER(email)=LOWER(#{searchString})" +
            "OR phone=#{searchString}"
    )
    @Result(column = "role", property = "role",
            typeHandler = RoleTypeHandler.class)
    Optional<UserModel> getUserByUsernameOrEmailOrPhone ( String searchString );

    @Select(" SELECT users.user_id AS id, " +
            "       username," +
            "       first_name," +
            "       last_name," +
            "email," +
            "phone," +
            "       password," +
            "       country, " +
            "       city, " +
            "       street," +
            "       house, " +
            "       role_id AS role " +
            "FROM users " +
            "INNER JOIN user_roles ON users.user_id = user_roles.user_id " +
            "WHERE username=#{username}"
    )
    @Result(column = "role", property = "role",
            typeHandler = RoleTypeHandler.class)
    UserProfile getByUsername ( @Param("username") String username );
}
