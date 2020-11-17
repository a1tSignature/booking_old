package ru.relex.miniBooking.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.miniBooking.bd.model.BookedInfoModel;
import ru.relex.miniBooking.bd.model.RoomModel;
import ru.relex.miniBooking.services.model.hotel.room.BookedInfo;
import ru.relex.miniBooking.services.model.hotel.room.NewRoom;
import ru.relex.miniBooking.services.model.hotel.room.Room;


@Mapper(componentModel = "spring")
public interface RoomStruct {
    @Mapping(target = "hotelId", source = "hotelId")
    @Mapping(target = "beds", source = "room.beds")
    @Mapping(target = "dailyPrice", source = "room.dailyPrice")
    @Mapping(target = "description", source = "room.description")
    RoomModel createRoom (NewRoom room,long hotelId);

    @Mapping(target = "hotelId", source = "hotelId")
    @Mapping(target = "beds", source = "beds")
    @Mapping(target = "dailyPrice", source = "dailyPrice")
    @Mapping(target = "description", source = "description")
    RoomModel createRoom (NewRoom room);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "hotelId", source = "model.hotelId")
    @Mapping(target = "beds", source = "model.beds")
    @Mapping(target = "dailyPrice", source = "model.dailyPrice")
    @Mapping(target = "description", source = "model.description")
    Room fromModel (RoomModel model, long id);


    @Mapping(target = "id", source = "model.id")
    @Mapping(target = "hotelId", source = "model.hotelId")
    @Mapping(target = "beds", source = "model.beds")
    @Mapping(target = "dailyPrice", source = "model.dailyPrice")
    @Mapping(target = "description", source = "model.description")
    Room fromModel (RoomModel model);

    @Mapping(target = "hotelId", source = "model.hotelId")
    @Mapping(target = "hotelName", source = "model.hotelName")
    @Mapping(target = "roomId", source = "model.roomId")
    @Mapping(target = "arrival", source = "model.arrival")
    @Mapping(target = "departure", source = "model.departure")
    @Mapping(target = "beds", source = "model.beds")
    @Mapping(target = "dailyPrice", source = "model.dailyPrice")
    @Mapping(target = "description", source = "model.description")
    BookedInfo fromModel (BookedInfoModel model);
}
