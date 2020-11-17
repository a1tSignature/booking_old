package ru.relex.miniBooking.services.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.relex.miniBooking.bd.model.BookedInfoModel;
import ru.relex.miniBooking.bd.model.RoomModel;
import ru.relex.miniBooking.services.model.hotel.room.BookedInfo;
import ru.relex.miniBooking.services.model.hotel.room.NewRoom;
import ru.relex.miniBooking.services.model.hotel.room.Room;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-14T17:51:22+0300",
    comments = "version: 1.4.0.Beta2, compiler: IncrementalProcessingEnvironment from gradle-language-java-4.10.3.jar, environment: Java 11.0.5 (JetBrains s.r.o)"
)
@Component
public class RoomStructImpl implements RoomStruct {

    @Override
    public RoomModel createRoom(NewRoom room, long hotelId) {
        if ( room == null ) {
            return null;
        }

        RoomModel roomModel = new RoomModel();

        if ( room != null ) {
            roomModel.setBeds( room.getBeds() );
            roomModel.setDailyPrice( room.getDailyPrice() );
            roomModel.setDescription( room.getDescription() );
        }
        roomModel.setHotelId( hotelId );

        return roomModel;
    }

    @Override
    public RoomModel createRoom(NewRoom room) {
        if ( room == null ) {
            return null;
        }

        RoomModel roomModel = new RoomModel();

        roomModel.setHotelId( room.getHotelId() );
        roomModel.setBeds( room.getBeds() );
        roomModel.setDailyPrice( room.getDailyPrice() );
        roomModel.setDescription( room.getDescription() );

        return roomModel;
    }

    @Override
    public Room fromModel(RoomModel model, long id) {
        if ( model == null ) {
            return null;
        }

        Room room = new Room();

        if ( model != null ) {
            room.setHotelId( model.getHotelId() );
            room.setBeds( model.getBeds() );
            room.setDailyPrice( model.getDailyPrice() );
            room.setDescription( model.getDescription() );
        }
        room.setId( id );

        return room;
    }

    @Override
    public Room fromModel(RoomModel model) {
        if ( model == null ) {
            return null;
        }

        Room room = new Room();

        room.setId( model.getId() );
        room.setHotelId( model.getHotelId() );
        room.setBeds( model.getBeds() );
        room.setDailyPrice( model.getDailyPrice() );
        room.setDescription( model.getDescription() );

        return room;
    }

    @Override
    public BookedInfo fromModel(BookedInfoModel model) {
        if ( model == null ) {
            return null;
        }

        BookedInfo bookedInfo = new BookedInfo();

        bookedInfo.setHotelId( model.getHotelId() );
        bookedInfo.setHotelName( model.getHotelName() );
        bookedInfo.setRoomId( model.getRoomId() );
        bookedInfo.setArrival( model.getArrival() );
        bookedInfo.setDeparture( model.getDeparture() );
        bookedInfo.setBeds( model.getBeds() );
        bookedInfo.setDailyPrice( model.getDailyPrice() );
        bookedInfo.setDescription( model.getDescription() );

        return bookedInfo;
    }
}
