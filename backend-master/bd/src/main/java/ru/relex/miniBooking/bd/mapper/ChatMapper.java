package ru.relex.miniBooking.bd.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.miniBooking.bd.model.MessageModel;
import ru.relex.miniBooking.commons.model.ChatListModel;
import ru.relex.miniBooking.commons.model.ChatModel;

import java.util.List;

@Mapper
public interface ChatMapper {
    @Insert("INSERT INTO chat_lines (chat_id,sent_by,message) values(#{message.chatId},#{message.sentBy},#{message.message})")
    void addMessage ( @Param("message") MessageModel messageModel );

    @Select ("INSERT INTO chats (landlord_id,tenant_id) values(#{landlordId},#{tenantId}) returning *")
    ChatModel createChat ( @Param("landlordId") long landlordId, @Param("tenantId") long tenantId );

    @Select ("SELECT * FROM chats where landlord_id = #{landlordId} And  tenant_id = #{tenantId}")
    ChatModel getChat ( @Param("landlordId") long landlordId, @Param("tenantId") long tenantId );



    List<MessageModel> getByChat ( @Param("chatId") long chatId );

    @Select("with ids as( select user_id from users where username = #{username}),\n" +
            "last_messages as (select distinct ON (chat_id) * from chat_lines order by chat_id,created_at desc )\n" +
            "Select chats.chat_id,username,message from chats join users on \n" +
            "((users.user_id=chats.tenant_id or users.user_id=chats.landlord_id) and (users.username!=#{username}) )\n" +
            " right join last_messages on last_messages.chat_id=chats.chat_id\n" +
            "where landlord_id in (select * from ids) or tenant_id in( select * from ids) \n" +
            "\t" +
            " ")
    @Results({@Result(property = "chatId", column = "chat_id"),
            @Result(property = "partnerName", column = "username"),
            @Result(property = "lastMessage", column = "message"),
          }
    )
    List<ChatListModel> getChatsByUser ( @Param("username") String username );
    @Select("select chat_id from chats where landlord_id=#{id} or tenant_id =#{id}")
    List<Long>  getChatsByUserId ( @Param("id") long userId );

}
