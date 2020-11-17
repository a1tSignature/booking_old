package ru.relex.miniBooking.services.model.chat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@JsonDeserialize
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
    long chatId;
    @Nullable
    String sentBy;
    @Length(min = 2, max = 100)
    @NotNull
    String message;
    @Nullable
    Date sentAt;

}
