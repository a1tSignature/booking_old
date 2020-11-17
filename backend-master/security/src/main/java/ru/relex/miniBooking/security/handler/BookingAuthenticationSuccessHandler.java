package ru.relex.miniBooking.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import ru.relex.miniBooking.bd.mapper.UserAuthMapper;
import ru.relex.miniBooking.bd.model.UserProfile;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.Set;

public class BookingAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public static final Logger logger = LoggerFactory.getLogger ( BookingAuthenticationSuccessHandler.class );

    private final UserAuthMapper userMapper;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper ( );

    @Autowired
    public BookingAuthenticationSuccessHandler ( UserAuthMapper userMapper ) {
        this.userMapper = userMapper;
    }

    @Override
    public void onAuthenticationSuccess ( HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication ) throws IOException, ServletException {
        onAuthenticationSuccess ( request, response, authentication );
    }

    @Override
    public void onAuthenticationSuccess ( HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws IOException {
        response.setStatus ( HttpServletResponse.SC_OK );
        var object = authentication.getPrincipal ( );
        if ( !( object instanceof UserDetails ) ) {
            logger.error ( "Expected user details but got {}", object.getClass ( ) );
            throw new ServerException ( "Unexpected authorization class" );
        }
        UserProfile profile = userMapper.getByUsername ( ( (UserDetails) object ).getUsername ( ) );
        SecurityContextHolder.getContext ( )
                .setAuthentication ( new UsernamePasswordAuthenticationToken (
                        profile,
                        null,
                        Set.of ( new SimpleGrantedAuthority ( "ROLE_" + profile.getRole ( ).name ( ) ) ) ) );
        OBJECT_MAPPER.writeValue ( response.getWriter ( ), profile );

    }
}
