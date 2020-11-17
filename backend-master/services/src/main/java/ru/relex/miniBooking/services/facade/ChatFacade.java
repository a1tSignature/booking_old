package ru.relex.miniBooking.services.facade;

import ru.relex.miniBooking.commons.model.ChatListModel;
import ru.relex.miniBooking.commons.model.ChatModel;
import ru.relex.miniBooking.services.model.chat.Message;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


public interface ChatFacade {
    void addMessage ( @Valid Message message, @NotNull String sentBy );

    ChatModel createChat ( long landlordId, long tenantId );
    List<ChatListModel> getMyChats(@NotNull String username);
    List<Message> getChatMessages(@NotNull long chatId);

}
