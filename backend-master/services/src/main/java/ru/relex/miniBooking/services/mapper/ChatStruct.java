package ru.relex.miniBooking.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.miniBooking.bd.model.MessageModel;
import ru.relex.miniBooking.services.model.chat.Message;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface ChatStruct {
    @Mapping(target = "chatId", source = "chatId")
    @Mapping(target = "sentBy", source = "sentBy")
    @Mapping(target = "message", source = "message.message")
    @Mapping(target = "sentAt", source = "createdAt")
    MessageModel toModel ( Message message, long chatId, Date createdAt,String sentBy );

    @Mapping(target = "chatId", source = "chatId")
    @Mapping(target = "sentBy", source = "sentBy")
    @Mapping(target = "message", source = "message.message")
    MessageModel toModel ( Message message, long chatId,String sentBy );

    @Mapping(target = "chatId", source = "message.chatId")
    @Mapping(target = "sentBy", source = "sentBy")
    @Mapping(target = "message", source = "message.message")
    MessageModel toModel ( Message message, String sentBy );


    @Mapping(target = "sentBy", source = "sentBy")
    @Mapping(target = "message", source = "message")
    @Mapping(target = "sentAt", source = "sentAt")
    @Mapping(target = "chatId", source = "chatId")
    Message fromModel ( MessageModel model );

}
