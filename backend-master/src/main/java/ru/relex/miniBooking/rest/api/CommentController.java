package ru.relex.miniBooking.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.relex.miniBooking.commons.model.ListModel;
import ru.relex.miniBooking.rest.exception.NoSuchObjectException;
import ru.relex.miniBooking.rest.util.UsernameExtractor;
import ru.relex.miniBooking.services.facade.CommentFacade;
import ru.relex.miniBooking.services.model.comment.Comment;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentFacade commentFacade;
    private final UsernameExtractor usernameExtractor;

    @Autowired
    public CommentController ( CommentFacade commentFacade, UsernameExtractor usernameExtractor ) {
        this.commentFacade = commentFacade;
        this.usernameExtractor = usernameExtractor;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Comment addComment ( @RequestBody Comment comment, Authentication authentication ) {
        String username = usernameExtractor.extract ( authentication );
        return commentFacade.addComment ( comment, username );

    }

    @GetMapping(path = "/user/{username}")
    ListModel<Comment> getByUser ( @PathVariable("username") String user ) {
        return commentFacade.getByUser ( user );

    }

    @GetMapping(path = "/hotel/{hotelid}")
    ListModel<Comment> getByHotel ( @PathVariable("hotelid") long hotelId ) {

        return commentFacade.getByHotel ( hotelId );

    }


    @DeleteMapping(path = "/{id}")
    void deleteComment ( @PathVariable("id") long commentId ) {
        commentFacade.deleteComment ( commentId );
    }

    @GetMapping(path = {"/{id}"})
    Comment getByID ( @PathVariable("id") long commentId ) {
        Comment comment = commentFacade.getById ( commentId );
        if ( comment == null )
            throw new NoSuchObjectException ( Comment.class );
        return commentFacade.getById ( commentId );
    }
}
