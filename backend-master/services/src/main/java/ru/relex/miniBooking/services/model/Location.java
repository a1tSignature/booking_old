package ru.relex.miniBooking.services.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.relex.miniBooking.services.validation.UserValidationErrors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Location {
    @Length(min = 1,
            max = 50,
            message = UserValidationErrors.LOCATION_DESCRIPTION_IS_INVALID)
    String country;
    @Length(min = 1,
            max = 50,
            message = UserValidationErrors.LOCATION_DESCRIPTION_IS_INVALID)
    String city;
    @Length(min = 1,
            max = 50,
            message = UserValidationErrors.LOCATION_DESCRIPTION_IS_INVALID)
    String street;
    @Length(min = 1,
            max = 5,
            message = UserValidationErrors.LOCATION_DESCRIPTION_IS_INVALID)
    String house;
}
