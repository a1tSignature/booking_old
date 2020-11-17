package ru.relex.miniBooking.services.validation;

import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class UserValidationErrors {


    public static final String EMAIL_HAS_INVALID_FORMAT = "EMAIL_HAS_INVALID_FORMAT";
    public static final String LAST_NAME_LENGTH_IS_INVALID = "LAST_NAME_LENGTH_IS_INVALID";
    public static final String FIRST_NAME_LENGTH_IS_INVALID = "FIRST_NAME_LENGTH_IS_INVALID";
    private static final String PERSONAL_INFO_MUST_BE_SET = "PERSONAL_INFO_MUST_BE_SET";
    public static final String ROLE_MUST_BE_SET = "ROLE_MUST_BE_SET";
    public static final String LOCATION_DESCRIPTION_IS_INVALID = "LOCATION_DESCRIPTION_IS_INVALID";

    private static final Map<String, String> ERRORS = Map.ofEntries(
            Map.entry(ROLE_MUST_BE_SET, "Role must be set"),
            Map.entry(PERSONAL_INFO_MUST_BE_SET, "Personal info must be set"),
            Map.entry(FIRST_NAME_LENGTH_IS_INVALID, "Invalid first name length"),
            Map.entry(LAST_NAME_LENGTH_IS_INVALID, "Invalid last name length"),
            Map.entry(EMAIL_HAS_INVALID_FORMAT, "Email should be of pattern ****@**.**"),
            Map.entry(LOCATION_DESCRIPTION_IS_INVALID, "Chek tour location description")
    );


    public static String getMessageByCode(String code) {
        return ERRORS.get(code);
    }

}
