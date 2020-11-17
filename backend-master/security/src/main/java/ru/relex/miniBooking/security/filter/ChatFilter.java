package ru.relex.miniBooking.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.relex.miniBooking.bd.mapper.ChatMapper;
import ru.relex.miniBooking.bd.model.UserProfile;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Component
public class ChatFilter extends GenericFilterBean {

    @Autowired
    ChatMapper chatMapper;

    @Override
    public void doFilter ( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {
        HttpServletRequest httpServletRequestRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String reqURI = httpServletRequestRequest.getRequestURI ( );
        UserProfile loggedUser = getUserFromSession ( );
        if ( loggedUser != null && reqURI.contains ( "/chat" ) ) {
            if ( reqURI.contains ( "/messages/" ) ) {
                validateGettingMessages ( loggedUser, reqURI );
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

    private void validateGettingMessages ( UserProfile profile, String requestURI )  {
        String chatIdString = requestURI.substring ( requestURI.lastIndexOf ( "/" ) + 1 );
        int chatId = Integer.parseInt( chatIdString );
        Long userId = profile.getId ();
        List<Long> chats = this.chatMapper.getChatsByUserId ( userId );


        for (Long existingChatId : chats
        ) {
            if ( existingChatId == chatId )
                return;
        }
        throw new AccessDeniedException ( "User dont have access to that chat" );
    }
}
