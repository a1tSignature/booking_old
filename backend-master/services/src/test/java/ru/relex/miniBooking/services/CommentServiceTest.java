package ru.relex.miniBooking.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.relex.miniBooking.bd.mapper.CommentMapper;
import ru.relex.miniBooking.bd.model.CommentModel;
import ru.relex.miniBooking.commons.model.ListModel;
import ru.relex.miniBooking.services.internal.CommentService;
import ru.relex.miniBooking.services.internal.impl.CommentServiceImpl;
import ru.relex.miniBooking.services.mapper.CommentStruct;
import ru.relex.miniBooking.services.model.comment.Comment;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.longThat;

public class CommentServiceTest {

    private static CommentStruct commentStructMock;
    private static CommentMapper commentMapperMock;
    private static CommentService commentService;
    private static LocalDate date = LocalDate.parse ( "9999-12-31" );
    private static Date exactDate = Date.from ( date.atStartOfDay ( ZoneId.of ( "Europe/Paris" ) ).toInstant ( ) );
    private static Comment comment = new Comment (1,1,"very bad","hotelHater","badbadbadbadbadbadbadbadbadbadbadbadbad",exactDate,1 );
    private static CommentModel commentModel = new CommentModel ( 1,1,"very bad","hotelHater","badbadbadbadbadbadbadbadbadbadbadbadbad",exactDate,1 );
    private static ListModel<CommentModel> expectedList = new ListModel<CommentModel> ( Arrays.asList ( commentModel ),1);
    private static ListModel<Comment> expectedCommentList = new ListModel<Comment> ( Arrays.asList ( comment ),1);
    private static ListModel<CommentModel> nullListModel = new ListModel<CommentModel> ( Collections.emptyList (),0);

    @BeforeEach
    void cleanUp ( ) {
        commentStructMock = Mockito.mock ( CommentStruct.class );
        Mockito.when ( commentStructMock.fromModel (commentModel  ) ).thenReturn ( comment );
        Mockito.when ( commentStructMock.fromModel ( commentModel,1,exactDate ) ).thenReturn ( comment );
        Mockito.when ( commentStructMock.toModel ( comment,"hotelHater" ) ).thenReturn ( commentModel );
        commentMapperMock = Mockito.mock ( CommentMapper.class );
        Mockito.when ( commentMapperMock.addComment ( commentModel ) ).thenReturn ( commentModel );
        Mockito.when ( commentMapperMock.findByHotel ( 1 ) ).thenReturn ( expectedList );
        Mockito.when ( commentMapperMock.findById ( 1 ) ).thenReturn ( commentModel );
        Mockito.when ( commentMapperMock.findByUser ( "hotelHater" ) ).thenReturn ( expectedList );
        Mockito.when ( commentMapperMock.findByHotel (longThat ( l->l!=1 ) )).thenReturn ( null );
        Mockito.when ( commentMapperMock.findByUser ( argThat ( a->!a.equals ( "hotelHater" ) ) ) ).thenReturn ( nullListModel );

        commentService = new CommentServiceImpl ( commentStructMock, commentMapperMock ) {
        };

    }
    @Test
    void findingCommentTest(){
        Assertions.assertEquals ( commentService.getByHotel ( 1).getList () ,expectedCommentList.getList ());
        Assertions.assertEquals ( commentService.getById (1) ,comment);
        Assertions.assertEquals ( commentService.getByUser ("hotelHater").getList () ,expectedCommentList.getList ());
    }
    @Test
    void nullForNonExistantComments(){
        Assertions.assertEquals ( commentService.getByHotel(123123).getAmount (),0);
        Assertions.assertEquals ( commentService.getById  (123) ,null);
        Assertions.assertEquals ( commentService.getByUser("somebody").getAmount () ,0);
    }
    @Test
    void addingCommentTest(){
        Assertions.assertEquals ( commentService.addComment (comment,"hotelHater") ,comment);

    }
}
