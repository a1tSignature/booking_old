package ru.relex.miniBooking.bd.model;

import lombok.*;
import ru.relex.miniBooking.commons.model.Role;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    private String firstName;
    private String lastName;
    private String email;

    public String getFirstName ( ) {
        return firstName;
    }

    public void setFirstName ( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName ( ) {
        return lastName;
    }

    public void setLastName ( String lastName ) {
        this.lastName = lastName;
    }

    public String getEmail ( ) {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getPhone ( ) {
        return phone;
    }

    public void setPhone ( String phone ) {
        this.phone = phone;
    }

    public String getUsername ( ) {
        return username;
    }

    public void setUsername ( String username ) {
        this.username = username;
    }

    public Role getRole ( ) {
        return role;
    }

    public void setRole ( Role role ) {
        this.role = role;
    }

    public String getCountry ( ) {
        return country;
    }

    public void setCountry ( String country ) {
        this.country = country;
    }

    public String getCity ( ) {
        return city;
    }

    public void setCity ( String city ) {
        this.city = city;
    }

    public String getStreet ( ) {
        return street;
    }

    public void setStreet ( String street ) {
        this.street = street;
    }

    public String getHouse ( ) {
        return house;
    }

    public void setHouse ( String house ) {
        this.house = house;
    }

    private String phone;
    private long id;

    public long getId ( ) {
        return id;
    }

    public void setId ( long id ) {
        this.id = id;
    }

    private String username;
    private Role role;
    String country;
    String city;
    String street;
    String house;
}
