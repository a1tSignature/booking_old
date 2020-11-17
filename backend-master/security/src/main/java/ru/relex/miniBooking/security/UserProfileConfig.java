package ru.relex.miniBooking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import ru.relex.miniBooking.bd.model.UserProfile;

@Configuration
public class UserProfileConfig {
    @Bean
    @Scope(
            proxyMode = ScopedProxyMode.TARGET_CLASS,
            scopeName = WebApplicationContext.SCOPE_REQUEST
    )
    UserProfile userProfile ( ) {

        var profileObj = SecurityContextHolder.getContext ( ).getAuthentication ( ).getPrincipal ( );
        System.out.println (profileObj );
        if ( profileObj instanceof UserProfile )
            return (UserProfile) profileObj;
        var profile = new UserProfile ( );
        profile.setUsername ( profileObj.toString ( ) );
        profile.setId ( -1 );
        return profile;
    }

}
