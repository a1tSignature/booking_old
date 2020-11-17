package ru.relex.miniBooking.services.internal;

import ru.relex.miniBooking.commons.model.ListModel;
import ru.relex.miniBooking.services.model.comment.Comment;

public interface CommentService {

    Comment getById ( long id );

    ListModel<Comment> getByHotel ( long hotelId );

    ListModel<Comment> getByUser ( String username );

    Comment addComment ( Comment comment,String creator );

    void deleteComment ( long id );
}
