package ru.relex.miniBooking.bd.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.miniBooking.commons.model.ImageModel;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select(" SELECT image" +
            " FROM hotel_images " +
            "WHERE hotel_id = #{hotelId}"
    )
    @Results({
            @Result(property = "lg",column = "image")
    })
    List<ImageModel> getImages ( @Param("hotelId") long hotelId );


    @Insert("INSERT INTO hotel_images (hotel_id,image) values(#{hotelId},#{fileName})")
    void saveFile ( @Param("fileName") String fileName, @Param("hotelId") long hotelId );


    @Select(" SELECT image FROM hotel_images  " +
            " WHERE hotel_id = #{hotelId} limit 1 "
    )
    @Results({
            @Result(property = "lg",column = "image")
    })
    ImageModel getImage(@Param("hotelId") long hotelId);
}
