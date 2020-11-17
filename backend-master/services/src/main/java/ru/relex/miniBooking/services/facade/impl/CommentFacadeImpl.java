package ru.relex.miniBooking.services.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.relex.miniBooking.commons.model.ListModel;
import ru.relex.miniBooking.services.facade.CommentFacade;
import ru.relex.miniBooking.services.internal.CommentService;
import ru.relex.miniBooking.services.meta.Facade;
import ru.relex.miniBooking.services.model.comment.Comment;

@Facade
public class CommentFacadeImpl implements CommentFacade {
    private final CommentService commentService;

    @Autowired
    public CommentFacadeImpl ( CommentService commentService ) {
        this.commentService = commentService;
    }

    @Override
    public Comment getById ( long id ) {
        return commentService.getById ( id );
    }

    @Override
    public ListModel<Comment> getByHotel ( long hotelId ) {
        return commentService.getByHotel ( hotelId );
    }

    @Override
    public ListModel<Comment> getByUser ( String username ) {
        return commentService.getByUser ( username );
    }

    @Override
    public Comment addComment ( Comment comment,String creator ) {
        return commentService.addComment ( comment,creator );
    }

    @Override
    public void deleteComment ( long id ) {
        commentService.deleteComment ( id );
    }
}
