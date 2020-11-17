package ru.relex.miniBooking.services.facade;

import ru.relex.miniBooking.commons.model.ListModel;
import ru.relex.miniBooking.services.model.comment.Comment;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface CommentFacade {
    Comment getById ( long id );

    ListModel<Comment> getByHotel ( long hotelId );

    ListModel<Comment> getByUser ( @NotNull String username );

    Comment addComment ( @Valid Comment comment ,@NotNull String creator);

    void deleteComment (long id );
}
