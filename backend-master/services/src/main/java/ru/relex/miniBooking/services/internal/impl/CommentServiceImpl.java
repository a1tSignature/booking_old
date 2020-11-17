package ru.relex.miniBooking.services.internal.impl;

import org.springframework.stereotype.Service;
import ru.relex.miniBooking.bd.mapper.CommentMapper;
import ru.relex.miniBooking.bd.model.CommentModel;
import ru.relex.miniBooking.commons.model.ListModel;
import ru.relex.miniBooking.services.internal.CommentService;
import ru.relex.miniBooking.services.mapper.CommentStruct;
import ru.relex.miniBooking.services.model.comment.Comment;

import java.util.Collections;

@Service
public class CommentServiceImpl implements CommentService {
    private  final CommentStruct commentStruct;
    private final CommentMapper commentMapper;

    public CommentServiceImpl ( CommentStruct commentStruct, CommentMapper commentMapper ) {
        this.commentStruct = commentStruct;
        this.commentMapper = commentMapper;
    }


    @Override
    public Comment getById ( long id ) {
        CommentModel model = commentMapper.findById ( id );
        return commentStruct.fromModel ( model );

    }

    @Override
    public ListModel<Comment> getByHotel ( long hotelId ) {
        ListModel<CommentModel> models = commentMapper.findByHotel ( hotelId );
        return getCommentListModel ( models );

    }

    private ListModel<Comment> getCommentListModel ( ListModel<CommentModel> models ) {
        if ( models != null ) {
            ListModel<Comment> comments = new ListModel<> ( );
            for (CommentModel model : models.list
            ) {
                comments.list.add ( commentStruct.fromModel ( model ) );
            }
            comments.setAmount ( models.getAmount ( ) );
            return comments;
        }
        return new ListModel<Comment> ( Collections.emptyList ( ), 0 );
    }

    @Override
    public ListModel<Comment> getByUser ( String username ) {
        ListModel<CommentModel> models = commentMapper.findByUser ( username );
        return getCommentListModel ( models );
    }

    @Override
    public Comment addComment ( Comment comment, String creator ) {
        CommentModel model = commentStruct.toModel ( comment, creator );
        CommentModel newComment = commentMapper.addComment ( model );
        return commentStruct.fromModel ( model, newComment.getId ( ), newComment.getCreatedAt ( ) );
    }

    @Override
    public void deleteComment ( long id ) {
        commentMapper.deleteComment ( id );

    }
}
