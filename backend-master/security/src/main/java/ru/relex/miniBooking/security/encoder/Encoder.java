package ru.relex.miniBooking.security.encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.relex.miniBooking.services.security.PasswordEncoder;

@Component
public class Encoder implements PasswordEncoder {

    private final org.springframework.security.crypto.password.PasswordEncoder encoder;

    @Autowired
    public Encoder ( org.springframework.security.crypto.password.PasswordEncoder encoder ) {
        this.encoder = encoder;
    }

    @Override
    public String encode ( String password ) {
        return encoder.encode ( password );
    }
}
