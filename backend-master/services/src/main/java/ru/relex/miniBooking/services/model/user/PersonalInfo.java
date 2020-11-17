package ru.relex.miniBooking.services.model.user;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import ru.relex.miniBooking.services.model.Location;
import ru.relex.miniBooking.services.validation.UserValidationErrors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PersonalInfo {
    @Nullable
    @Length(
            min = 1,
            max = 50,
            message = UserValidationErrors.FIRST_NAME_LENGTH_IS_INVALID
    )
    String firstName;
    @Nullable
    @Length(
            min = 1,
            max = 50,
            message = UserValidationErrors.LAST_NAME_LENGTH_IS_INVALID
    )
    String lastName;
    @Pattern(regexp = "[a-zA-Z\\d-_.]+@[a-zA-Z\\d-_.]{3,}", message = UserValidationErrors.EMAIL_HAS_INVALID_FORMAT)
    String email;
    @Pattern(regexp = "\\d{5,15}", message = "PHONE_FORMAT_IS_INVALID")
    String phoneNumber;

   public String getFullName ( ) {
        if ( getFirstName ( ) != null && getLastName ( ) != null )
            return getFirstName ( ) + ' ' + getLastName ( );
        if ( getFirstName ( ) != null )
            return getFirstName ( );
        if ( getLastName ( ) != null )
            return ' ' + getLastName ( );
        return "";
    }

    @NotNull
    Location location;

}
