package ru.relex.miniBooking.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.relex.miniBooking.commons.model.Role;
import ru.relex.miniBooking.services.model.Location;
import ru.relex.miniBooking.services.model.user.NewUser;
import ru.relex.miniBooking.services.model.user.PersonalInfo;
import ru.relex.miniBooking.services.validation.UserValidationErrors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@SpringBootTest(classes = Validator.class)
@Import(ValidationAutoConfiguration.class)
public class NewUserValidatorTest {

    @Autowired
    Validator validator;

    @Test
    void checkUserValid ( ) {
        NewUser newUser = NewUser.builder ( ).personalInfo ( new PersonalInfo (
                "12",
                "11",
                "1",
                "12332",
                new Location (
                        "123", "1232", "1232131", "123"
                )
        ) ).role ( Role.TENANT ).password ( "123" ).username ( "123" ).build ( );
      Set<ConstraintViolation<NewUser>>violationSet= validator.validate ( newUser );
      Assertions.assertEquals ( 1,violationSet.size () );
       Assertions.assertEquals (violationSet.stream ().findFirst ().orElseThrow ().getMessage () , UserValidationErrors.EMAIL_HAS_INVALID_FORMAT);
    }
}
