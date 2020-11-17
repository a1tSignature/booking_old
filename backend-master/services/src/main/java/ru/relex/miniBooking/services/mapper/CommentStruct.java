package ru.relex.miniBooking.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.miniBooking.bd.model.CommentModel;
import ru.relex.miniBooking.services.model.comment.Comment;

import java.util.Date;

@Mapper( componentModel = "spring")
public interface CommentStruct {
    @Mapping(target = "hotelId", source = "comment.hotelId")
    @Mapping(target = "title", source = "comment.title")
    @Mapping(target = "createdBy", source = "creator")
    @Mapping(target = "text", source = "comment.text")
    @Mapping(target = "createdAt", source = "comment.createdAt")
    @Mapping(target = "rating", source = "comment.rating")
    CommentModel toModel ( Comment comment, String creator );

    @Mapping(target = "hotelId", source = "comment.hotelId")
    @Mapping(target = "title", source = "comment.title")
    @Mapping(target = "createdBy", source = "comment.createdBy")
    @Mapping(target = "text", source = "comment.text")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "rating", source = "comment.rating")
    Comment fromModel ( CommentModel comment, long id, Date createdAt );

    @Mapping(target = "hotelId", source = "comment.hotelId")
    @Mapping(target = "title", source = "comment.title")
    @Mapping(target = "createdBy", source = "comment.createdBy")
    @Mapping(target = "text", source = "comment.text")
    @Mapping(target = "id", source = "comment.id")
    @Mapping(target = "createdAt", source = "comment.createdAt")
    @Mapping(target = "rating", source = "comment.rating")
    Comment fromModel ( CommentModel comment );

}
