package ru.relex.miniBooking.services.model.hotel.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DateOfResidence {
    Date arrivalDate;
    Date departureDate;
}
