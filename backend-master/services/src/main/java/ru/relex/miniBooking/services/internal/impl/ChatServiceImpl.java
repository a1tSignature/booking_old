package ru.relex.miniBooking.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.miniBooking.bd.mapper.ChatMapper;
import ru.relex.miniBooking.bd.model.MessageModel;
import ru.relex.miniBooking.commons.model.ChatListModel;
import ru.relex.miniBooking.commons.model.ChatModel;
import ru.relex.miniBooking.services.internal.ChatService;
import ru.relex.miniBooking.services.mapper.ChatStruct;
import ru.relex.miniBooking.services.model.chat.Message;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatStruct chatStruct;
    private final ChatMapper chatMapper;

    @Autowired
    public ChatServiceImpl(ChatStruct chatStruct, ChatMapper chatMapper) {
        this.chatStruct = chatStruct;
        this.chatMapper = chatMapper;
    }

    @Override
    public void addMessage(Message message, String sentBy) {
        final var model = chatStruct.toModel(message, sentBy);
        chatMapper.addMessage(model);
    }

    @Override
    public ChatModel createChat(long landlordId, long tenantId) {
        var chat = chatMapper.getChat(landlordId, tenantId);
        if (chat == null)
            chat = this.chatMapper.createChat(landlordId, tenantId);
        return chat;
    }

    @Override
    public List<ChatListModel> getMyChats(String username) {
        return chatMapper.getChatsByUser(username);
    }

    @Override
    public List<Message> getChatMessages(long chatId) {
        List<MessageModel> models = chatMapper.getByChat(chatId);
        List<Message> messages = new ArrayList<>();
        for (MessageModel model : models) {
            messages.add(chatStruct.fromModel(model));
        }
        return messages;
    }
}
