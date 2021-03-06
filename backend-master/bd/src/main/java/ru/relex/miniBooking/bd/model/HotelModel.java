package ru.relex.miniBooking.bd.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class    HotelModel {
    long id;
    String name;
    String country;
    String city;
    String street;
    String house;
    String description;
    String[] amenities;
    Date createdAt;
}
