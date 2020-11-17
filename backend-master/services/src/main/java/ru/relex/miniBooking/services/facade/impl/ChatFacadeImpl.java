package ru.relex.miniBooking.services.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.relex.miniBooking.commons.model.ChatModel;
import ru.relex.miniBooking.services.facade.ChatFacade;
import ru.relex.miniBooking.services.internal.ChatService;
import ru.relex.miniBooking.services.meta.Facade;
import ru.relex.miniBooking.commons.model.ChatListModel;
import ru.relex.miniBooking.services.model.chat.Message;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Facade
public class ChatFacadeImpl implements ChatFacade {
    private final ChatService chatService;
    @Autowired
    public ChatFacadeImpl ( ChatService chatService ) {
        this.chatService = chatService;
    }

    @Override
    public void addMessage ( @Valid Message message , @NotNull String sentBy) {
        chatService.addMessage(message,sentBy);
    }

    @Override
    public ChatModel createChat ( long landlordId, long tenantId ) {
       return this.chatService.createChat ( landlordId, tenantId );
    }

    @Override
    public List<ChatListModel> getMyChats ( @NotNull String username ) {
        return chatService.getMyChats(username);
    }

    @Override
    public List<Message> getChatMessages ( @NotNull long chatId ) {
        return chatService.getChatMessages( chatId);
    }
}
