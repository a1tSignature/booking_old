package ru.relex.miniBooking.security.filter;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonAuthenthicationFilter extends AuthenticationFilter {
    public JsonAuthenthicationFilter ( AuthenticationManager authenticationManager, AuthenticationConverter authenticationConverter ) {
        super ( authenticationManager, authenticationConverter );
    }

    public JsonAuthenthicationFilter ( AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver, AuthenticationConverter authenticationConverter ) {
        super ( authenticationManagerResolver, authenticationConverter );
    }

    @Override
    protected void doFilterInternal ( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ) throws ServletException, IOException {
        super.doFilterInternal ( request, response, filterChain );

    }

    @Override
    protected boolean shouldNotFilter ( HttpServletRequest request ) {
        return false;
    }
}
