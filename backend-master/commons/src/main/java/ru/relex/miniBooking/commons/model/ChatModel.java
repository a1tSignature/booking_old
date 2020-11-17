package ru.relex.miniBooking.commons.model;

public class ChatModel {
    long chatId;

    public ChatModel ( long chatId, long landlordId, long tenantId ) {
        this.chatId = chatId;
        this.landlordId = landlordId;
        this.tenantId = tenantId;
    }

    public long getChatId ( ) {
        return chatId;
    }

    public void setChatId ( long chatId ) {
        this.chatId = chatId;
    }

    public ChatModel ( ) {
    }

    public long getLandlordId ( ) {
        return landlordId;
    }

    public void setLandlordId ( long landlordId ) {
        this.landlordId = landlordId;
    }

    public long getTenantId ( ) {
        return tenantId;
    }

    public void setTenantId ( long tenantId ) {
        this.tenantId = tenantId;
    }

    public ChatModel ( long landlordId, long tenantId ) {
        this.landlordId = landlordId;
        this.tenantId = tenantId;
    }

    long landlordId;
    long tenantId;
}


