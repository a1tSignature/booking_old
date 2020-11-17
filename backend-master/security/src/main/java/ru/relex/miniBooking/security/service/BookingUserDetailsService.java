package ru.relex.miniBooking.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.relex.miniBooking.bd.mapper.UserAuthMapper;
import ru.relex.miniBooking.security.model.BookingUserDetails;

@Service
public class BookingUserDetailsService implements UserDetailsService {
    private final UserAuthMapper userMapper;

    @Autowired
    public BookingUserDetailsService ( UserAuthMapper userMapper ) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername ( String searchString ) throws UsernameNotFoundException {
        return userMapper.getUserByUsernameOrEmailOrPhone ( searchString )
                .map ( BookingUserDetails::new )
                .orElseThrow ( ( ) -> new UsernameNotFoundException ( "User with name [" + searchString + "] not found" ) );
    }
}
