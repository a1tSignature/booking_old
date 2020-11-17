package ru.relex.miniBooking.services.model.hotel.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@JsonDeserialize
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookedInfo {

    @JsonProperty("hotelId")
    long hotelId;

    @JsonProperty("hotelName")
    String hotelName;

    @JsonProperty("roomId")
    long roomId;

    @JsonProperty("arrivalDate")
    Date arrival;

    @JsonProperty("departureDate")
    Date departure;

    @JsonProperty("bedsCount")
    int beds;

    @JsonProperty("dailyPrice")
    int dailyPrice;

    @JsonProperty("description")
    String description;
}
