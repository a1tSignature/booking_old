package ru.relex.miniBooking.security.filter;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.NoSuchObjectException;

@Component

public class ExceptionHandlingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal ( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ) throws ServletException, IOException {
        try {
            doFilter ( request, response, filterChain );
        } catch ( NoSuchObjectException e ) {
            response.setStatus ( HttpServletResponse.SC_NOT_FOUND );
            response.getWriter ( ).write ( "No such " + e.getMessage ( ) + " object" );
        }
        catch ( AccessDeniedException e ){
            response.setStatus ( HttpServletResponse.SC_FORBIDDEN );
            response.getWriter ( ).write ( e.getMessage () );
        }


    }

}

