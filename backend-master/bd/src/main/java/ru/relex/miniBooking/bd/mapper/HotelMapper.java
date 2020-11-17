package ru.relex.miniBooking.bd.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.ArrayTypeHandler;
import ru.relex.miniBooking.bd.model.HotelDetailModel;
import ru.relex.miniBooking.bd.model.HotelModel;
import ru.relex.miniBooking.commons.model.ListModel;

import java.util.Date;

@Mapper
public interface HotelMapper {


    HotelModel createHotel(HotelModel hotel);


    @Insert ("INSERT INTO hotels_created_by (user_id,hotel_id) values(#{user_id},#{hotel_id})")
    void addCreatedBy(@Param ("hotel_id") long hotelId, @Param ("user_id") long userId);


    //language=postgreSQL
    @Select ("  SELECT *" +
            "FROM hotels  join hotels_created_by on hotels.hotel_id = hotels_created_by.hotel_id join users on hotels_created_by.user_id =users.user_id " +
            "            WHERE hotels.hotel_id =#{id} "
    )
    @Results ({
            @Result (property = "amenities", column = "amenities", typeHandler = ArrayTypeHandler.class),
            @Result (property = "creator", column = "username"),
            @Result (property = "creatorId", column = "user_id")
    })
    HotelDetailModel findById(@Param ("id") long id);


    //language=postgreSQL
    @Select ("SELECT   COUNT(*) OVER (), * FROM hotels " +
            "WHERE city = #{city} AND country = #{country} LIMIT #{quantity} OFFSET #{offset} "
    )
    @ResultMap ("HotelList")
    ListModel<HotelModel> findByLocation(@Param ("country") String country, @Param ("city") String city, @Param ("offset") long offset, @Param ("quantity") long quantity);


    //language=postgreSQL
    @Select (
            "SELECT   COUNT(*) OVER (), * from hotels group by hotel_id LIMIT #{quantity} OFFSET #{offset} ")
    @ResultMap ("HotelList")
    ListModel<HotelModel> findAll(@Param ("offset") long offset, @Param ("quantity") long quantity);

 /*   {@Result(property = "list.id", column = "hotel_id"),
        @Result(property = "list.name", column = "name"),
        @Result(property = "list.country", column = "country"),
        @Result(property = "list.city", column = "city"),
        @Result(property = "list.street", column = "street"),
        @Result(property = "list.house", column = "house"),
        @Result(property = "list.description", column = "description"),
        @Result(property = "list.amenities", column = "amenities", typeHandler = ArrayTypeHandler.class),
        @Result(property = "list.createdAt", column = "created_at")

    }*/

    // ?? TIMESTAMP #{arrivalDate} '' Date
    //language=postgreSQL
    @Select (" WITH rooms_reservations AS (SELECT * FROM booked_rooms), " +
            "      rooms_c AS (SELECT * FROM rooms where room_id in (SELECT room_id FROM rooms_reservations where  " +
            "                ((rooms_reservations.arrival < #{arrivalDate} " +
            "                   AND rooms_reservations.departure > #{arrivalDate} )  " +
            "                 OR  " +
            "                  (rooms_reservations.arrival < #{departureDate}   " +
            "                  AND rooms_reservations.departure > #{departureDate} ))) ) " +
            " SELECT   COUNT(*) OVER (), *  FROM hotels  " +
            "            WHERE (hotel_id in (SELECT hotel_id FROM rooms where room_id not in (select room_id from rooms_c) " +
            "                OR room_id NOT IN (SELECT room_id FROM rooms_reservations)))  " +
            "                AND hotel_id in (SELECT hotel_id FROM rooms)  " +
            "                AND city = #{city} AND country = #{country} " +
            "             LIMIT #{quantity} OFFSET #{offset} "
    )
    @ResultMap ("HotelList")
    ListModel<HotelModel> findByLocationAndDate(
            @Param ("country") String country,
            @Param ("city") String city,
            @Param ("arrivalDate") Date arrivalDate,
            @Param ("departureDate") Date departureDate,
            @Param ("offset") long offset, @Param ("quantity") long quantity);


    void updateHotel(HotelModel hotel);

    //language=postgreSQL
    // @Delete("DELETE FROM hotels USING rooms WHERE hotels.hotel_id=rooms.hotel_id AND hotels.hotel_id = #{id}")
    @Delete ("DELETE FROM hotels USING hotels_created_by WHERE hotels_created_by.hotel_id=hotels.hotel_id AND hotels.hotel_id=#{id}")
    boolean deleteHotel(@Param ("id") long id);

    @Select ("SELECT user_id FROM hotels_created_by where hotel_id=#{hotelId}")
    Long getHotelCreatorId(@Param ("hotelId") long hotelId);

    //language=postgreSQL
    @Select ("WITH id AS (SELECT user_id FROM users where username = #{username}) \n" +
            "SELECT   COUNT(*) OVER (), * FROM hotels \n" +
            "INNER JOIN hotels_created_by on hotels_created_by.hotel_id=hotels.hotel_id \n" +
            "WHERE hotels_created_by.user_id in (SELECT * FROM id) "
    )
    @ResultMap ("HotelList")
    ListModel<HotelModel> findByUser(@Param ("username") String username);

}
