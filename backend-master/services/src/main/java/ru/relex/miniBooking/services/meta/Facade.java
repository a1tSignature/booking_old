package ru.relex.miniBooking.services.meta;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;

@Documented
@Component
@Validated
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface Facade {
}
