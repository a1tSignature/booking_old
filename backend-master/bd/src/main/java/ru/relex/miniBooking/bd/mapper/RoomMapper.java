package ru.relex.miniBooking.bd.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.ArrayTypeHandler;
import ru.relex.miniBooking.bd.model.BookedInfoModel;
import ru.relex.miniBooking.bd.model.HotelModel;
import ru.relex.miniBooking.bd.model.RoomModel;

import java.util.Date;
import java.util.List;

@Mapper
public interface RoomMapper {


    RoomModel addRoom (RoomModel room);


    //language=postgreSQL
    @Select("SELECT * FROM rooms " +
            "WHERE hotel_id = #{hotelId} "
    )
    @Results({@Result(property = "id", column = "room_id"),
            @Result(property = "hotelId", column = "hotel_id"),
            @Result(property = "beds", column = "beds"),
            @Result(property = "dailyPrice", column = "daily_Price"),
            @Result(property = "description", column = "description")}
    )
    List<RoomModel> findByHotelId ( @Param("hotelId") long hotelId );


    // ?? TIMESTAMP #{arrivalDate} '' Date
    //language=postgreSQL
    @Select(" WITH booked_rooms AS (SELECT * FROM booked_rooms)  " +
            " SELECT * FROM rooms  " +
            " WHERE room_id not in " +
            " ( " +
            "   SELECT room_id FROM booked_rooms where not " +
            "     ((arrival < #{arrivalDate} AND departure < #{arrivalDate} )   " +
            "     OR  " +
            "     (arrival > #{departureDate} AND departure > #{departureDate} )))  " +
            " AND hotel_id = #{hotelId} "
    )
    @Results({@Result(property = "id", column = "room_id"),
            @Result(property = "hotelId", column = "hotel_id"),
            @Result(property = "beds", column = "beds"),
            @Result(property = "dailyPrice", column = "daily_Price"),
            @Result(property = "description", column = "description")}
    )
    List<RoomModel> findByHotelIdAndDate (
            @Param("hotelId") long hotelId,
            @Param("arrivalDate") Date arrivalDate,
            @Param("departureDate") Date departureDate
    );


    @Select("WITH booked_rooms_  " +
            "       AS (SELECT * FROM booked_rooms WHERE user_login = #{userLogin}), " +
            "     rooms_  " +
            "       AS (SELECT * FROM rooms where room_id in (select room_id from booked_rooms_)), " +
            "     hotels_  " +
            "       AS (SELECT hotel_id, name FROM hotels where hotel_id in (SELECT hotel_id FROM rooms)), " +
            " " +
            "     search_info_table(hotel_id, hotel_name, room_id, arrival, departure, beds, daily_price, description)  " +
            "       AS ( " +
            "        SELECT h.hotel_id, h.name, br.room_id, br.arrival, br.departure, r.beds, r.daily_price, r.description " +
            "        FROM booked_rooms_ br, rooms_ r, hotels_ h " +
            "        where br.room_id = r.room_id and h.hotel_id = r.hotel_id " +
            "  ) " +
            "SELECT * FROM search_info_table;"
    )
    @Results({
            @Result(property = "hotelId", column = "hotel_id"),
            @Result(property = "hotelName", column = "hotel_name"),
            @Result(property = "roomId", column = "room_id"),
            @Result(property = "arrival", column = "arrival"),
            @Result(property = "departure", column = "departure"),
            @Result(property = "beds", column = "beds"),
            @Result(property = "dailyPrice", column = "daily_price"),
            @Result(property = "description", column = "description")
    })
    List<BookedInfoModel> getRoomsBookedInfoByUserLogin(@Param("userLogin")String userLogin);


        void updateRoom (RoomModel room);


    // ?? TIMESTAMP #{arrivalDate} '' Date
    //language=postgreSQL
    @Insert( "insert into booked_rooms values " +
            "(#{id}, #{arrivalDate}, #{departureDate}, #{userLogin})"
    )
    void bookRoom (
            @Param("id") long id,
            @Param("userLogin") String userLogin,
            @Param("arrivalDate") Date arrivalDate,
            @Param("departureDate") Date departureDate
    );


    //language=postgreSQL
    @Delete("DELETE FROM rooms WHERE room_id=#{id}")
    boolean deleteRoom ( @Param("id") long id );
}
