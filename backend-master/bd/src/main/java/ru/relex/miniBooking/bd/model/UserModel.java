package ru.relex.miniBooking.bd.model;

import lombok.*;
import ru.relex.miniBooking.commons.model.Role;

import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {
        "firstName",
        "lastName",
        "password",
        "createdAt",
        "active",
        "locked",
        "email",
        "phone",
        "country",
        "city",
        "street",
        "house"
})

public class UserModel {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean active;
    private boolean locked;
    private String username;
    private String password;
    private Instant createdAt;
    private Role role;
    String country;
    String city;
    String street;
    String house;
}
