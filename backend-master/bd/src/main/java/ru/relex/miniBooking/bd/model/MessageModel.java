package ru.relex.miniBooking.bd.model;

import org.springframework.lang.Nullable;

import java.util.Date;

public class MessageModel {
    long chatId;
    String sentBy;
    String message;
    Date sentAt;
    public long getChatId ( ) {
        return chatId;
    }

    public void setChatId ( long chatId ) {
        this.chatId = chatId;
    }

    public String getSentBy ( ) {
        return sentBy;
    }

    public void setSentBy ( String sentBy ) {
        this.sentBy = sentBy;
    }

    public String getMessage ( ) {
        return message;
    }

    public void setMessage ( String message ) {
        this.message = message;
    }

    public Date getSentAt ( ) {
        return sentAt;
    }

    public void setSentAt ( Date sentAt ) {
        this.sentAt = sentAt;
    }

    public MessageModel ( long chatId, String sentBy, String message, Date sentAt ) {
        this.chatId = chatId;
        this.sentBy = sentBy;
        this.message = message;

        this.sentAt = sentAt;

    }


}
