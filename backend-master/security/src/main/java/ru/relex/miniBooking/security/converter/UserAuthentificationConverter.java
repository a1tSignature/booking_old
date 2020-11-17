package ru.relex.miniBooking.security.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import ru.relex.miniBooking.security.model.UsernamePasswordRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserAuthentificationConverter implements AuthenticationConverter {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper ( );

    @Override
    public Authentication convert ( HttpServletRequest request ) {
        UsernamePasswordRequest usernamePasswordRequest;
        try (var inputStream = request.getInputStream ( )) {
            usernamePasswordRequest = OBJECT_MAPPER.readValue ( inputStream, UsernamePasswordRequest.class );
        } catch ( IOException e ) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken (
                usernamePasswordRequest.getUsername ( ),
                usernamePasswordRequest.getPassword ( )
        );

    }

}
