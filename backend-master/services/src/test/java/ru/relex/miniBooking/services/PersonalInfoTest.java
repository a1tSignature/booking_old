package ru.relex.miniBooking.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.relex.miniBooking.services.model.Location;
import ru.relex.miniBooking.services.model.user.PersonalInfo;

import java.util.stream.Stream;

public class PersonalInfoTest {
    String firstName = "John";
    String lastName = "Doe";

    @Test
    @DisplayName("Check fullname")
    void getFullName ( ) {

        PersonalInfo personalInfo = new PersonalInfo ( firstName, lastName, "", "", new Location ( "", "", " ", "" ) );
        Assertions.assertEquals ( "John Doe", personalInfo.getFullName ( ) );
    }

    @Test
    @DisplayName("Check empty fullname")
    void getEmptyFullName ( ) {
        PersonalInfo personalInfo = new PersonalInfo ( null, null, "", "", new Location ( "", "", " ", "" ) );
        Assertions.assertEquals ( "", personalInfo.getFullName ( ) );
    }

    @Test
    @DisplayName("totally empty")
    void getFail ( ) {
        PersonalInfo personalInfo = new PersonalInfo ( null, null, "", "", new Location ( "", "", " ", "" ) );
        Assertions.assertNotEquals ( "123", personalInfo.getFullName ( ) );
    }

    @Test
    @DisplayName("Check empty only lastName")
    void getLastNullName ( ) {
        PersonalInfo personalInfo = new PersonalInfo ( firstName, null, "", "", new Location ( "", "", " ", "" ) );
        Assertions.assertEquals ( firstName, personalInfo.getFullName ( ) );
    }

    @ParameterizedTest
    @MethodSource("supplyFullNameExamples")
    void makeFullNameTests ( FullNameBundle fullNameBundle ) {
        final var pi = new PersonalInfo ( fullNameBundle.firstName, fullNameBundle.lastName, "", "", new Location ( "", "", " ", "" ) );
        Assertions.assertEquals ( fullNameBundle.fullName, pi.getFullName ( ) );
    }

    static class FullNameBundle {
        static String firstName;
        static String lastName;
        static String fullName;

        public FullNameBundle ( String firstName, String lastName, String fullName ) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = fullName;
        }


    }

   static Stream<FullNameBundle> supplyFullNameExamples ( ) {
        return Stream.of (
                new FullNameBundle ( "John", "Doe", "John Doe" ),
                new FullNameBundle ( null, "Doe", "Doe" ),
                new FullNameBundle ( "John", null, "John" ),
                new FullNameBundle ( null, null, "" )
        );
    }
}
