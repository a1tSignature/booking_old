package ru.relex.miniBooking.bd.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.miniBooking.bd.model.CommentModel;
import ru.relex.miniBooking.commons.model.ListModel;

@Mapper
public interface CommentMapper {
    //language=postgreSQL
    @Select(" SELECT *" +
            "FROM comments " +
            "WHERE id = #{id}"
    )
    @Result(property = "rating", column = "rating")
    CommentModel findById ( @Param("id") long id );

    //language=postgreSQL
    @Select("SELECT   COUNT(*) OVER (), * FROM COMMENTS WHERE hotel_id=#{hotel_id}")
    @ResultMap ( "CommentList" )
    ListModel<CommentModel> findByHotel ( @Param("hotel_id") long hotelId );

    CommentModel addComment ( CommentModel comment );

    @Delete("DELETE  FROM comments where id = #{id}")
    void deleteComment ( @Param("id") long id );

    @Select("SELECT   COUNT(*) OVER (), * FROM COMMENTS WHERE created_by=#{username}")
    @ResultMap ( "CommentList" )
    ListModel<CommentModel> findByUser ( @Param("username") String username );

}

