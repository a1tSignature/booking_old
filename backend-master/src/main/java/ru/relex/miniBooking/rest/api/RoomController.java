package ru.relex.miniBooking.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.relex.miniBooking.rest.exception.NoSuchObjectException;
import ru.relex.miniBooking.services.facade.RoomFacade;
import ru.relex.miniBooking.services.model.hotel.room.BookedInfo;
import ru.relex.miniBooking.services.model.hotel.room.DateOfResidence;
import ru.relex.miniBooking.services.model.hotel.room.NewRoom;
import ru.relex.miniBooking.services.model.hotel.room.Room;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
//TODO:rooms bd integration

    private RoomFacade roomFacade;

    @Autowired
    public RoomController ( final RoomFacade roomFacade ) {
        this.roomFacade = roomFacade;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/{hotelId}")
    Room addRoom ( @RequestBody NewRoom newRoom, @PathVariable("hotelId") long hotelId ) {
        final var room = roomFacade.addRoom ( newRoom, hotelId );
        if ( room == null ) {
            throw new NoSuchObjectException ( Room.class );
        }
        return room;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/{hotelId}/multiple")
    void addRooms ( @RequestBody List<NewRoom> newRooms, @PathVariable("hotelId") long hotelId ) {
        roomFacade.addRooms ( newRooms, hotelId );

    }

    @PutMapping(path = "/{id}")
    Room updateRoom ( @PathVariable("id") long id, @RequestBody NewRoom room ) {
        return roomFacade.updateRoom ( id, room );
    }

    @PutMapping(path = "/{id}/book")
    DateOfResidence bookRoom (
            @PathVariable("id") long id,
            @RequestParam("userLogin") String userLogin,
            @RequestParam("arrivalDate") Date arrivalDate,
            @RequestParam("dateOfDeparture") Date dateOfDeparture
    ) {
        final var returnDateOfResidence = roomFacade.bookRoom ( id, userLogin, arrivalDate, dateOfDeparture );
        if ( returnDateOfResidence == null ) {
            throw new NoSuchObjectException ( Room.class );
        }
        return returnDateOfResidence;
    }

    @GetMapping(path = "/booked/{userLogin}")
    List<BookedInfo> getRoomsBookedInfoByUserLogin ( @PathVariable("userLogin") String userLogin ) {
        return roomFacade.getRoomsBookedInfoByUserLogin ( userLogin );

    }

    @GetMapping(path = "/{hotelId}")
    List<Room> getRoomsByHotelId ( @PathVariable("hotelId") long hotelId ) {

        return roomFacade.getRoomsByHotelId ( hotelId );

    }

    @GetMapping(path = "/{hotelId}/byDate")
    List<Room> getRoomsByHotelIdAndDate (
            @PathVariable("hotelId") long hotelId,
            @RequestParam("arrivalDate") Date arrivalDate,
            @RequestParam("dateOfDeparture") Date dateOfDeparture
    ) {

        return roomFacade.getRoomsByHotelIdAndDate ( hotelId, arrivalDate, dateOfDeparture );

    }

    @DeleteMapping(path = "/{hotelId}/{id}")
    boolean deleteRoom ( @PathVariable("hotelId") long hotelId, @PathVariable("id") long id ) {
        if ( roomFacade.deleteRoomByHotelIdAndId ( hotelId, id ) ) {
            return true;
        }
        throw new NoSuchObjectException ( Room.class );
    }
}
