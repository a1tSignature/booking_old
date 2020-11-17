package ru.relex.miniBooking.bd.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentModel {
    long id;
    long hotelId;
    String title;
    String createdBy;
    String text;
    Date createdAt;
    int rating;
}
