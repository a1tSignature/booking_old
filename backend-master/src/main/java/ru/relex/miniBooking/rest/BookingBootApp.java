package ru.relex.miniBooking.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.relex.miniBooking.bd.DataBaseConfig;
import ru.relex.miniBooking.security.SecurityConfig;
import ru.relex.miniBooking.services.ServicesConfiguration;

@SpringBootApplication
@Import({ServicesConfiguration.class,
        SecurityConfig.class,
        DataBaseConfig.class
})
public class BookingBootApp {
    public static void main ( String[] args ) {
        SpringApplication.run ( BookingBootApp.class, args );
    }
}
