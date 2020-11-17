package ru.relex.miniBooking.services.model.comment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@JsonDeserialize
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    long id;
    long hotelId;
    @Length(min = 5, max = 30)
    @NotNull
    String title;
    @Nullable
    String createdBy;
    @Length(min = 30, max = 200)
    @NotNull
    String text;
    @Nullable
    Date createdAt;
    int rating;
}
