package ru.relex.miniBooking.services.model.user;

import lombok.*;
import ru.relex.miniBooking.commons.model.Role;
import ru.relex.miniBooking.services.validation.UserValidationErrors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
abstract class BaseUser {
     @NotNull(message = UserValidationErrors.ROLE_MUST_BE_SET)
     Role role;
     @Valid
     @NotNull
     PersonalInfo personalInfo;

}
