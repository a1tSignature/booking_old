package ru.relex.miniBooking.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.relex.miniBooking.bd.mapper.HotelMapper;
import ru.relex.miniBooking.bd.model.UserProfile;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.NoSuchObjectException;

@Component
public class HotelFilter extends GenericFilterBean {
    //TODO: new table with creators, adding id's to it and tests
    @Autowired
    HotelMapper hotelMapper;

    @Override
    public void doFilter ( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {
        HttpServletRequest httpServletRequestRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        UserProfile loggedUser = getUserFromSession ( );
        if ( loggedUser != null && !httpServletRequestRequest.getRequestURI().contains("/book")) {
            switch ( ( httpServletRequestRequest.getMethod ( ) ) ) {
                case "DELETE":
                case "PUT":
                    validateDeletionOrUpdate ( loggedUser, httpServletRequestRequest, httpServletResponse );
                    break;


            }
        }
        chain.doFilter ( request, response );
    }

    private UserProfile getUserFromSession ( ) {
        var object = SecurityContextHolder.getContext ( ).getAuthentication ( ).getPrincipal ( );
        if ( object instanceof UserProfile )
            return (UserProfile) object;
        else return null;
    }

    private void validateDeletionOrUpdate ( UserProfile profile, HttpServletRequest request, HttpServletResponse httpServletResponse ) throws IOException {
        String requestString = request.getRequestURI ( );
        requestString = requestString.substring ( requestString.lastIndexOf ( '/' ) + 1 );
        long hotelId = Long.valueOf ( requestString );
        long userId = profile.getId ( );
        try {
            if ( userId != hotelMapper.getHotelCreatorId ( hotelId ) )
                throw  new AccessDeniedException ( "You dont have access to that hotel" );
        } catch ( NullPointerException e ) {
            throw new NoSuchObjectException ( "Hotel" );
        }

    }

}
