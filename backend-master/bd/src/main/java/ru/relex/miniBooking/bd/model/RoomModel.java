package ru.relex.miniBooking.bd.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoomModel {
    long id;
    long hotelId;
    int beds;
    int dailyPrice;
    String description;
}