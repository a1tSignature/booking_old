package ru.relex.miniBooking.services.facade;

import ru.relex.miniBooking.services.model.hotel.room.BookedInfo;
import ru.relex.miniBooking.services.model.hotel.room.DateOfResidence;
import ru.relex.miniBooking.services.model.hotel.room.NewRoom;
import ru.relex.miniBooking.services.model.hotel.room.Room;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public interface RoomFacade {
    void addRooms(@Valid List<NewRoom> rooms, @NotNull long hotelId);

    Room addRoom(@Valid NewRoom room, @NotNull long hotelId);

    DateOfResidence bookRoom(@NotNull long id, @NotNull String userLogin, @NotNull Date arrivalDate, @NotNull Date dateOfDeparture);

    List<Room> getRoomsByHotelId (@NotNull long hotelId);

    boolean deleteRoomByHotelIdAndId (@NotNull long hotelId, @NotNull long id);

    List<Room> getRoomsByHotelIdAndDate(@NotNull long hotelId, @NotNull Date arrivalDate, @NotNull Date dateOfDeparture);

    Room updateRoom(@NotNull long id, @NotNull NewRoom room);

    List<BookedInfo> getRoomsBookedInfoByUserLogin(@NotNull String userLogin);
}
