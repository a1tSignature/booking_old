package ru.relex.miniBooking.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;
import ru.relex.miniBooking.bd.model.UserModel;
import ru.relex.miniBooking.services.model.user.ExistingUser;
import ru.relex.miniBooking.services.model.user.NewUser;
import ru.relex.miniBooking.services.model.user.UpdatableUser;
import ru.relex.miniBooking.services.model.user.UserStatus;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.Instant;

@Mapper(componentModel = "spring")
public interface UserStruct {
    @Mapping(target = "firstName", source = "user.personalInfo.firstName")
    @Mapping(target = "lastName", source = "user.personalInfo.lastName")
    @Mapping(target = "email", source = "user.personalInfo.email")
    @Mapping(target = "phone", source = "user.personalInfo.phoneNumber")
    @Mapping(target = "active", constant = "false")
    @Mapping(target = "locked", constant = "false")
    @Mapping(target = "country", source = "user.personalInfo.location.country")
    @Mapping(target = "city", source = "user.personalInfo.location.city")
    @Mapping(target = "street", source = "user.personalInfo.location.street")
    @Mapping(target = "house", source = "user.personalInfo.location.house")
    @Mapping(target = "password", source = "password")
    UserModel fromNewUser(NewUser user, String password);

    @Mapping(target = "personalInfo.firstName", source = "model.firstName")
    @Mapping(target = "personalInfo.lastName", source = "model.lastName")
    @Mapping(target = "personalInfo.email", source = "model.email")
    @Mapping(target = "personalInfo.phoneNumber", source = "model.phone")
    @Mapping(target = "personalInfo.location.country",source = "model.country")
    @Mapping(target = "personalInfo.location.city",source = "model.city")
    @Mapping(target =  "personalInfo.location.street",source = "model.street")
    @Mapping(target =  "personalInfo.location.house",source = "model.house")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "status.active", source = "model.active")
    @Mapping(target = "status.blocked", source = "model.locked")
    ExistingUser toExistingUser(UserModel model, long id, Instant createdAt);


    @Mapping(target = "personalInfo.firstName", source = "byId.firstName")
    @Mapping(target = "personalInfo.lastName", source = "byId.lastName")
    @Mapping(target = "personalInfo.email", source = "byId.email")
    @Mapping(target = "personalInfo.phoneNumber", source = "byId.phone")
    @Mapping(target = "personalInfo.location.country",source = "byId.country")
    @Mapping(target = "personalInfo.location.city",source = "byId.city")
    @Mapping(target =  "personalInfo.location.street",source = "byId.street")
    @Mapping(target =  "personalInfo.location.house",source = "byId.house")
    @Mapping(target = "status.active", source = "byId.active")
    @Mapping(target = "status.blocked", source = "byId.locked")
    ExistingUser toExistingUser(UserModel byId);


    @Mapping(target = "id", source = "prev.id")
    @Mapping(target = "username", source = "prev.username")
    @Mapping(target = "createdAt", source = "prev.createdAt")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "role", source = "user.role")
    @Mapping(target = "firstName", source = "user.personalInfo.firstName")
    @Mapping(target = "lastName", source = "user.personalInfo.lastName")
    @Mapping(target = "email", source = "user.personalInfo.email")
    @Mapping(target = "phone", source = "user.personalInfo.phoneNumber")
    @Mapping(target = "active", source = "user.status.active")
    @Mapping(target = "locked", source = "user.status.blocked")
    @Mapping(target = "country", source = "user.personalInfo.location.country")
    @Mapping(target = "city", source = "user.personalInfo.location.city")
    @Mapping(target = "street", source = "user.personalInfo.location.street")
    @Mapping(target = "house", source = "user.personalInfo.location.house")
    UserModel mergeUpdated ( UpdatableUser user,UserModel prev);


    @Mapping(target = "status", source = "updateData.status")
    @Mapping(target = "personalInfo", source = "updateData.personalInfo")
    @Mapping(target = "role", source = "updateData.role")
    ExistingUser merge(ExistingUser user, UpdatableUser updateData);


    @Qualifier
    @Retention(RetentionPolicy.CLASS)
    @interface DefaultStatusMapper {
    }


    @Mapping(target = "id", source = "newId")
    @Mapping(target = "status", source = "user", qualifiedBy = DefaultStatusMapper.class)
    ExistingUser fromNewUser ( NewUser user, long newId );

    @DefaultStatusMapper
    default UserStatus defaultStatusMapper ( @SuppressWarnings("unused") NewUser user ) {
        return new UserStatus ( );
    }


}
