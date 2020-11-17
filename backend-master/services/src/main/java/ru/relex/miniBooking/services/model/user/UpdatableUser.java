package ru.relex.miniBooking.services.model.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.relex.miniBooking.commons.model.Role;
import ru.relex.miniBooking.services.validation.UserValidationErrors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonDeserialize
@AllArgsConstructor

public class UpdatableUser extends  BaseUser {
    @NotNull
    String password;

    UserStatus status;

    @Builder
    public UpdatableUser ( @NotNull(message = UserValidationErrors.ROLE_MUST_BE_SET) Role role, @Valid @NotNull PersonalInfo personalInfo, String password ) {
        super ( role, personalInfo );
        this.password = password;
        this.status = new UserStatus ();
    }
}
