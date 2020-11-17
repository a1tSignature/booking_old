package ru.relex.miniBooking.services.model.hotel.room;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.List;

@Getter
@Setter
@JsonDeserialize
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Room {
    long id;
    long hotelId;
    int beds;
    int dailyPrice;
    String description;
}
