package ru.relex.miniBooking.services.internal;

import ru.relex.miniBooking.commons.model.ChatListModel;
import ru.relex.miniBooking.commons.model.ChatModel;
import ru.relex.miniBooking.services.model.chat.Message;

import java.util.List;

public interface ChatService {
    void addMessage ( Message message,  String sentBy );

    ChatModel createChat ( long landlordId, long tenantId );

    List<ChatListModel> getMyChats ( String username );
    List<Message> getChatMessages(long chatId);
}
