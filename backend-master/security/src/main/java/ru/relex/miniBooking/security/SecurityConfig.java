package ru.relex.miniBooking.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.relex.miniBooking.bd.mapper.UserAuthMapper;
import ru.relex.miniBooking.commons.model.Role;
import ru.relex.miniBooking.security.converter.UserAuthentificationConverter;
import ru.relex.miniBooking.security.filter.ChatFilter;
import ru.relex.miniBooking.security.filter.ExceptionHandlingFilter;
import ru.relex.miniBooking.security.filter.HotelFilter;
import ru.relex.miniBooking.security.filter.JsonAuthenthicationFilter;
import ru.relex.miniBooking.security.handler.BookingAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(
        jsr250Enabled = true,
        prePostEnabled = true,
        securedEnabled = true
)

@ComponentScan(basePackageClasses = SecurityConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final UserAuthMapper userAuthMapper;
    private final ExceptionHandlingFilter handlingFilter;
    private final HotelFilter hotelFilter;
    private final ChatFilter chatFilter;


    @Autowired
    public SecurityConfig ( UserDetailsService userDetailsService, UserAuthMapper userAuthMapper, ExceptionHandlingFilter handlingFilter, HotelFilter hotelFilter, ChatFilter chatFilter ) {
        this.userDetailsService = userDetailsService;
        this.userAuthMapper = userAuthMapper;
        this.handlingFilter = handlingFilter;
        this.hotelFilter = hotelFilter;
        this.chatFilter = chatFilter;
    }

    @Bean
    PasswordEncoder passwordEncoder ( ) {
        return new Pbkdf2PasswordEncoder ( "", 178_123, 512 );
    }

    @Override
    public void configure ( WebSecurity web ) throws Exception {
        super.configure ( web );
    }


    @Override
    protected void configure ( AuthenticationManagerBuilder auth ) throws Exception {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider ( );
        provider.setPasswordEncoder ( passwordEncoder ( ) );
        provider.setUserDetailsService ( userDetailsService );
        auth.authenticationProvider ( provider );
    }


    @Override
    protected void configure ( HttpSecurity http ) throws Exception {
        final var authFilter = new JsonAuthenthicationFilter ( authenticationManager ( ), new UserAuthentificationConverter ( ) );
        authFilter.setRequestMatcher ( new AntPathRequestMatcher ( "/auth/login", "POST" ) );
        authFilter.setSuccessHandler ( new BookingAuthenticationSuccessHandler ( userAuthMapper ) );
        authFilter.setFailureHandler ( new AuthenticationFailureHandler ( ) {
            private final Logger logger = LoggerFactory.getLogger ( SecurityConfig.class );

            @Override
            public void onAuthenticationFailure ( HttpServletRequest request, HttpServletResponse response, AuthenticationException exception ) {
                logger.error ( "Error", exception );
                response.setStatus ( HttpServletResponse.SC_UNAUTHORIZED );

            }
        } );
        var encoder = passwordEncoder ( );
        http
                .cors ( ).and ( )
                .csrf ( )./*csrfTokenRepository ( csrfTokenRepository ( ) ).ignoringAntMatchers ( "/auth/login" )*/disable ( )
                .authorizeRequests ( )
                .antMatchers ( HttpMethod.GET, "/user/**", "/user" ).permitAll ( )
                .antMatchers ( "/auth/**" ).not ( ).authenticated ( )
                .antMatchers ( "/chat/**" ).permitAll ()
                .antMatchers ( HttpMethod.POST, "/user" ).not ( ).authenticated ( )
                .antMatchers ( HttpMethod.GET, "/hotel", "/hotel/**" ).permitAll ( )
                .antMatchers ( "/hotel", "/hotel/**" ).hasRole ( Role.LANDLORD.toString ( ) )
                .antMatchers ( HttpMethod.GET, "/room", "/room/**" ).permitAll ()
                .antMatchers ( HttpMethod.POST, "/room", "/room/**" ).hasRole ( Role.LANDLORD.toString ( ) )
                .antMatchers ( HttpMethod.GET, "/comments/**" ).permitAll ( )
                .antMatchers ( HttpMethod.GET, "/image/*" ).permitAll ( )
                .antMatchers (  "/auth/*","/login"  ).permitAll ( )
                .antMatchers (  "/room/**/book", "/room/**/book/**" ).hasRole ( Role.TENANT.toString ( ) )
                .antMatchers (  "/room/booked", "/room/booked/**" ).hasRole ( Role.TENANT.toString ( ) )
                .antMatchers (  HttpMethod.GET, "/image/first", "/image/first/**" ).permitAll()
                .anyRequest ( ).authenticated ( )
                .and ( )
                .addFilterAfter ( authFilter, LogoutFilter.class )
                .addFilterAfter ( hotelFilter, SessionManagementFilter.class )
                .addFilterAfter ( chatFilter,HotelFilter.class )
                .addFilterBefore ( handlingFilter, SecurityContextPersistenceFilter.class )
                .logout ( ).logoutUrl ( "/auth/logout" ).logoutSuccessUrl ( "/user/current" );


    }


    @Bean
    CorsConfigurationSource corsConfigurationSource ( ) {
        CorsConfiguration config = new CorsConfiguration ( );
/*
        config.setAllowedOrigins ( Collections.singletonList ( "http://localhost:4230" ) );
*/
        config.setAllowedOrigins ( Collections.singletonList ( "*" ) );
        config.setAllowedMethods ( Collections.singletonList ( "*" ) );
        config.setAllowedHeaders ( Collections.singletonList ( "*" ) );
        config.setAllowCredentials ( true );
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource ( );
        source.registerCorsConfiguration ( "/**", config );
        return source;
    }


    @Override
    @Bean
    protected AuthenticationManager authenticationManager ( ) throws Exception {
        return super.authenticationManager ( );
    }

    /*private CsrfTokenRepository csrfTokenRepository ( ) {
        var csrfRepo = CookieCsrfTokenRepository.withHttpOnlyFalse ( );
        csrfRepo.setCookiePath ( "/" );
        csrfRepo.setHeaderName ( "BOOKING-TOKEN" );
        return csrfRepo;
    }*/


}
