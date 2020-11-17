package ru.relex.miniBooking.services.validation;

import java.util.Map;

public class RoomValidationErrors {

    private RoomValidationErrors() {}

    public static final String HOTELID_IS_INVALID = "HOTELID_IS_INVALID";
    public static final String NUBEROFBEDS_IS_INVALID = "NUBEROFBEDS_IS_INVALID";
    public static final String DESCRIPTION_IS_INVALID = "DESCRIPTION_IS_INVALID";
    public static final String DAILYPRICE_IS_INVALID = "DAILYPRICE_IS_INVALID";
    private static final Map<String, String> ERRORS = Map.ofEntries (
            Map.entry ( HOTELID_IS_INVALID, "Hotel with such id does not exist" ),
            Map.entry ( NUBEROFBEDS_IS_INVALID,"Minimum number of beds - 1" ),
            Map.entry ( DESCRIPTION_IS_INVALID,
                    "The minimum description length is 30 characters, the maximum is 500 characters" ),
            Map.entry( DAILYPRICE_IS_INVALID, "Minimum daily price - 1")
    );

    public static String getMessageByCode (String code ) {
        return ERRORS.get ( code );
    }
}
