package ru.relex.miniBooking.services.internal.impl;

import org.springframework.stereotype.Service;
import ru.relex.miniBooking.bd.mapper.RoomMapper;
import ru.relex.miniBooking.bd.model.BookedInfoModel;
import ru.relex.miniBooking.bd.model.RoomModel;
import ru.relex.miniBooking.services.internal.RoomService;
import ru.relex.miniBooking.services.mapper.RoomStruct;
import ru.relex.miniBooking.services.model.hotel.room.BookedInfo;
import ru.relex.miniBooking.services.model.hotel.room.DateOfResidence;
import ru.relex.miniBooking.services.model.hotel.room.NewRoom;
import ru.relex.miniBooking.services.model.hotel.room.Room;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomMapper roomMapper;
    private final RoomStruct roomStruct;

    public RoomServiceImpl(RoomStruct roomStruct, RoomMapper roomMapper ) {
        this.roomStruct = roomStruct;
        this.roomMapper = roomMapper;
    }

    @Override
    public void addRooms ( @NotNull List<NewRoom> newRooms, @NotNull long hotelId ) {
        for (NewRoom newRoom : newRooms
                ) {
            final var model = roomStruct.createRoom(newRoom,hotelId);
            final var newModel = roomMapper.addRoom(model);
        }
    }

    @Override
    public Room addRoom(@NotNull NewRoom newRoom, @NotNull long hotelId) {
        final var model = roomStruct.createRoom(newRoom,hotelId);
        final var newModel = roomMapper.addRoom(model);
        return roomStruct.fromModel(model, newModel.getId());
    }

    @Override
    public DateOfResidence bookRoom(@NotNull long id, @NotNull String userLogin, @NotNull Date arrivalDate, @NotNull Date dateOfDeparture) {
        roomMapper.bookRoom(id, userLogin, arrivalDate, dateOfDeparture);
        return new DateOfResidence(arrivalDate, dateOfDeparture);
    }

    @Override
    public List<Room> getRoomsByHotelId(@NotNull long hotelId) {
        List<RoomModel> models = roomMapper.findByHotelId(hotelId);
        List<Room> rooms = new ArrayList<>();
        for (RoomModel model : models) {
            rooms.add(roomStruct.fromModel(model));
        }
        return rooms;
    }

    @Override
    public List<Room> getRoomsByHotelIdAndDate(@NotNull long hotelId, @NotNull Date arrivalDate, @NotNull Date dateOfDeparture) {
        List<RoomModel> models = roomMapper.findByHotelIdAndDate(hotelId, arrivalDate, dateOfDeparture);
        List<Room> rooms = new ArrayList<>();
        for (RoomModel model : models) {
            rooms.add(roomStruct.fromModel(model));
        }
        return rooms;
    }

    @Override
    public boolean deleteRoomByHotelIdAndId(@NotNull long hotelId, @NotNull long id) {
        return roomMapper.deleteRoom(id);
    }

    @Override
    public Room updateRoom(@NotNull long id, @NotNull NewRoom room) {
        final var model = roomStruct.createRoom(room);
        model.setId(id);
        roomMapper.updateRoom(model);
        return roomStruct.fromModel(model);
    }

    @Override
    public List<BookedInfo> getRoomsBookedInfoByUserLogin(@NotNull String userLogin) {
        List<BookedInfoModel> models = roomMapper.getRoomsBookedInfoByUserLogin(userLogin);
        List<BookedInfo> items = new ArrayList<>();
        for (BookedInfoModel model : models) {
            model.setArrival(fixDate(model.getArrival()));
            model.setDeparture(fixDate(model.getDeparture()));
            items.add(roomStruct.fromModel(model));
        }
        return items;
    }

    @NotNull
    private Date fixDate(@NotNull Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, 3);
        return c.getTime();
    }
}
