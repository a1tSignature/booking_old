package ru.relex.miniBooking.services.internal;

import ru.relex.miniBooking.services.model.hotel.room.BookedInfo;
import ru.relex.miniBooking.services.model.hotel.room.DateOfResidence;
import ru.relex.miniBooking.services.model.hotel.room.NewRoom;
import ru.relex.miniBooking.services.model.hotel.room.Room;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public interface RoomService {
    void addRooms(@NotNull List<NewRoom> newRooms, @NotNull long hotelId);

    Room addRoom(@NotNull NewRoom newRoom, @NotNull long hotelId);

    DateOfResidence bookRoom(@NotNull long id, @NotNull String userLogin, @NotNull Date arrivalDate, @NotNull Date dateOfDeparture);

    List<Room> getRoomsByHotelId(@NotNull long hotelId);

    List<Room> getRoomsByHotelIdAndDate(@NotNull long hotelId, @NotNull Date arrivalDate, @NotNull Date dateOfDeparture);

    boolean deleteRoomByHotelIdAndId( @NotNull long hotelId, @NotNull long id);

    Room updateRoom(@NotNull long id, @NotNull NewRoom room);

    List<BookedInfo> getRoomsBookedInfoByUserLogin(@NotNull String userLogin);
}
