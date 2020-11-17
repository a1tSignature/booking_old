package ru.relex.miniBooking.rest.util;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.relex.miniBooking.bd.model.UserProfile;

@Component
public class IdExtractor {
    public long extract ( Authentication authentication ) {
        if ( authentication != null ) {
            if ( authentication.getPrincipal ( ) instanceof UserProfile ) {
                UserProfile profile = (UserProfile) authentication.getPrincipal ( );
                return profile.getId ( );
            }
        }
        return 3;
    }
}
