package ru.relex.miniBooking.services.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.relex.miniBooking.services.facade.RoomFacade;
import ru.relex.miniBooking.services.internal.RoomService;
import ru.relex.miniBooking.services.meta.Facade;
import ru.relex.miniBooking.services.model.hotel.room.BookedInfo;
import ru.relex.miniBooking.services.model.hotel.room.DateOfResidence;
import ru.relex.miniBooking.services.model.hotel.room.NewRoom;
import ru.relex.miniBooking.services.model.hotel.room.Room;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Facade
public class RoomFacadeImpl implements RoomFacade {

    RoomService roomService;

    @Autowired
    public RoomFacadeImpl ( RoomService roomService ) {
        this.roomService = roomService;
    }

    @Override
    public void addRooms ( @Valid List<NewRoom> rooms, @NotNull long hotelId ) {
         roomService.addRooms(rooms, hotelId);
    }

    @Override
    public Room addRoom(@Valid NewRoom newRoom, @NotNull long hotelId) {
        return roomService.addRoom(newRoom, hotelId);
    }

    @Override
    public DateOfResidence bookRoom(@NotNull long id, @NotNull String userLogin, @NotNull Date arrivalDate, @NotNull Date dateOfDeparture) {
        return roomService.bookRoom(id, userLogin, arrivalDate, dateOfDeparture);
    }

    @Override
    public List<Room> getRoomsByHotelId(@NotNull long hotelId) {
        return roomService.getRoomsByHotelId(hotelId);
    }

    @Override
    public List<Room> getRoomsByHotelIdAndDate(@NotNull long hotelId, @NotNull Date arrivalDate, @NotNull Date dateOfDeparture) {
        return roomService.getRoomsByHotelIdAndDate(hotelId, arrivalDate, dateOfDeparture);
    }

    @Override
    public Room updateRoom(@NotNull long id, @NotNull NewRoom room) {
        return roomService.updateRoom(id, room);
    }

    @Override
    public List<BookedInfo> getRoomsBookedInfoByUserLogin(@NotNull String userLogin) {
        return roomService.getRoomsBookedInfoByUserLogin(userLogin);
    }

    @Override
    public boolean deleteRoomByHotelIdAndId(@NotNull long hotelId, @NotNull long id) {
        return roomService.deleteRoomByHotelIdAndId(hotelId, id);
    }
}
