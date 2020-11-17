package ru.relex.miniBooking.services.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.relex.miniBooking.commons.model.Role;
import ru.relex.miniBooking.services.validation.UserValidationErrors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
public class ExistingUser extends BaseUser {
    long id;
    String username;
    UserStatus status;
    Instant createdAt;

    @Builder
    public ExistingUser ( @NotNull(message = UserValidationErrors.ROLE_MUST_BE_SET) Role role, @Valid @NotNull PersonalInfo personalInfo, long id, String username, UserStatus status ) {
        super (role, personalInfo);
        this.id = id;
        this.username = username;
        this.status = status;
    }
}
