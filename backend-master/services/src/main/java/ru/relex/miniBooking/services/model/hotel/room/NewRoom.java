package ru.relex.miniBooking.services.model.hotel.room;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.relex.miniBooking.services.validation.RoomValidationErrors;

import javax.validation.constraints.Min;

@Getter
@Setter
@JsonDeserialize
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewRoom {
    long hotelId;

    @Min(value = 1, message = RoomValidationErrors.NUBEROFBEDS_IS_INVALID)
    int beds;

    @Min(value = 1, message = RoomValidationErrors.DAILYPRICE_IS_INVALID)
    int dailyPrice;

    @Length(min = 30, max = 500, message = RoomValidationErrors.DESCRIPTION_IS_INVALID)
    String description;
}
