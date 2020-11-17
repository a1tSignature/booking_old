package ru.relex.miniBooking.services.model.hotel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.relex.miniBooking.services.model.Location;
import ru.relex.miniBooking.services.model.hotel.room.Room;
import ru.relex.miniBooking.services.validation.HotelValidationErrors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@JsonDeserialize
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewHotel {
    @Length(min = 4)
    String name;
    @NotNull(message = HotelValidationErrors.LOCATION_DESCRIPTION_IS_INVALID)
    Location location;
    @Length(min = 50)
    String description;
    @Size(min = 3, message = HotelValidationErrors.AMENITIES_SIZE_IS_INVALID)
    List<String> amenities;
}
