package ru.relex.miniBooking.services.mapper;

import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.relex.miniBooking.bd.model.MessageModel;
import ru.relex.miniBooking.services.model.chat.Message;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-14T17:51:22+0300",
    comments = "version: 1.4.0.Beta2, compiler: IncrementalProcessingEnvironment from gradle-language-java-4.10.3.jar, environment: Java 11.0.5 (JetBrains s.r.o)"
)
@Component
public class ChatStructImpl implements ChatStruct {

    @Override
    public MessageModel toModel(Message message, long chatId, Date createdAt, String sentBy) {
        if ( message == null && createdAt == null && sentBy == null ) {
            return null;
        }

        String message1 = null;
        if ( message != null ) {
            message1 = message.getMessage();
        }
        Date sentAt = null;
        if ( createdAt != null ) {
            sentAt = createdAt;
        }
        String sentBy1 = null;
        if ( sentBy != null ) {
            sentBy1 = sentBy;
        }
        long chatId1 = 0L;
        chatId1 = chatId;

        MessageModel messageModel = new MessageModel( chatId1, sentBy1, message1, sentAt );

        return messageModel;
    }

    @Override
    public MessageModel toModel(Message message, long chatId, String sentBy) {
        if ( message == null && sentBy == null ) {
            return null;
        }

        String message1 = null;
        Date sentAt = null;
        if ( message != null ) {
            message1 = message.getMessage();
            sentAt = message.getSentAt();
        }
        String sentBy1 = null;
        if ( sentBy != null ) {
            sentBy1 = sentBy;
        }
        long chatId1 = 0L;
        chatId1 = chatId;

        MessageModel messageModel = new MessageModel( chatId1, sentBy1, message1, sentAt );

        return messageModel;
    }

    @Override
    public MessageModel toModel(Message message, String sentBy) {
        if ( message == null && sentBy == null ) {
            return null;
        }

        long chatId = 0L;
        String message1 = null;
        Date sentAt = null;
        if ( message != null ) {
            chatId = message.getChatId();
            message1 = message.getMessage();
            sentAt = message.getSentAt();
        }
        String sentBy1 = null;
        if ( sentBy != null ) {
            sentBy1 = sentBy;
        }

        MessageModel messageModel = new MessageModel( chatId, sentBy1, message1, sentAt );

        return messageModel;
    }

    @Override
    public Message fromModel(MessageModel model) {
        if ( model == null ) {
            return null;
        }

        Message message = new Message();

        message.setSentBy( model.getSentBy() );
        message.setMessage( model.getMessage() );
        message.setSentAt( model.getSentAt() );
        message.setChatId( model.getChatId() );

        return message;
    }
}
