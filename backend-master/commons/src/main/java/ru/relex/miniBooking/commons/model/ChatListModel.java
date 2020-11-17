package ru.relex.miniBooking.commons.model;


public class ChatListModel {
    long chatId;

    public long getChatId ( ) {
        return chatId;
    }

    public void setChatId ( long chatId ) {
        this.chatId = chatId;
    }

    public String getPartnerName ( ) {
        return partnerName;
    }

    public void setPartnerName ( String partnerName ) {
        this.partnerName = partnerName;
    }

    public String getLastMessage ( ) {
        return lastMessage;
    }

    public void setLastMessage ( String lastMessage ) {
        this.lastMessage = lastMessage;
    }


    public ChatListModel ( long chatId, String partnerName, String lastMessage ) {
        this.chatId = chatId;
        this.partnerName = partnerName;
        this.lastMessage = lastMessage;
    }

    String partnerName;
    String lastMessage;
}
