package ru.relex.miniBooking.bd.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookedInfoModel {
    long hotelId;
    String hotelName;
    long roomId;
    Date arrival;
    Date departure;
    int beds;
    int dailyPrice;
    String description;
}
