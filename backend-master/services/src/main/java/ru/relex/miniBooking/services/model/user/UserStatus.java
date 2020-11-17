package ru.relex.miniBooking.services.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStatus {
    boolean isActive;
    boolean isBlocked;

    public UserStatus ( ) {
        this.isActive = false;
        this.isBlocked = false;
    }
}
