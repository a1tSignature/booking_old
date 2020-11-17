package ru.relex.miniBooking.services.model.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import ru.relex.miniBooking.commons.model.Role;
import ru.relex.miniBooking.services.validation.UserValidationErrors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonDeserialize
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "password",callSuper = true)
public class NewUser extends BaseUser {
    String username;
    String password;
    @Builder
    public NewUser ( @NotNull(message = UserValidationErrors.ROLE_MUST_BE_SET) Role role, @Valid @NotNull PersonalInfo personalInfo, String username, String password ) {
        super ( role, personalInfo );
        this.username = username;
        this.password = password;
    }


}
