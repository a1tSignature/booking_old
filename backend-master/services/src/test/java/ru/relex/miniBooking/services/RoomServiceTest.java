package ru.relex.miniBooking.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.DoesNothing;
import ru.relex.miniBooking.bd.mapper.CommentMapper;
import ru.relex.miniBooking.bd.mapper.RoomMapper;
import ru.relex.miniBooking.bd.model.RoomModel;
import ru.relex.miniBooking.services.internal.RoomService;
import ru.relex.miniBooking.services.internal.impl.CommentServiceImpl;
import ru.relex.miniBooking.services.internal.impl.RoomServiceImpl;
import ru.relex.miniBooking.services.mapper.CommentStruct;
import ru.relex.miniBooking.services.mapper.RoomStruct;
import ru.relex.miniBooking.services.model.hotel.room.DateOfResidence;
import ru.relex.miniBooking.services.model.hotel.room.NewRoom;
import ru.relex.miniBooking.services.model.hotel.room.Room;

import java.rmi.NoSuchObjectException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

public class RoomServiceTest {
    private static RoomStruct roomStructMock;
    private static RoomMapper roomMapperMock;
    private static RoomService roomService;
    private static LocalDate date = LocalDate.parse ( "9999-12-31" );
    private static Date exactDate = Date.from ( date.atStartOfDay ( ZoneId.of ( "Europe/Paris" ) ).toInstant ( ) );
    private static NewRoom newRoom = new NewRoom (1,2,3,"room");
    private static NewRoom newRoomUpdate = new NewRoom (1,3,3,"updated");
    private  static Room room = new Room ( 1,1,2,3,"room" );
    private  static Room roomUpdated = new Room ( 1,1,3,3,"updated" );
    private  static RoomModel roomModel = new RoomModel ( 1,1,2,3,"room" );
    private  static RoomModel roomModelUpdate = new RoomModel ( 1,1,2,3,"updated" );

    private static List<RoomModel> expectedModelsList =  Arrays.asList ( roomModel ) ;
    private static List<Room> expectedList =  Arrays.asList ( room ) ;

    @BeforeEach
    void cleanUp ( ) {
        roomStructMock = Mockito.mock ( RoomStruct.class );
        Mockito.when ( roomStructMock.fromModel (roomModel  ) ).thenReturn ( room );
        Mockito.when ( roomStructMock.fromModel (roomModelUpdate  ) ).thenReturn ( roomUpdated );
        Mockito.when ( roomStructMock.fromModel ( roomModel,1 ) ).thenReturn ( room );
        Mockito.when ( roomStructMock.createRoom ( newRoom ) ).thenReturn ( roomModel );
        Mockito.when ( roomStructMock.createRoom ( newRoomUpdate ) ).thenReturn ( roomModelUpdate );
        Mockito.when ( roomStructMock.createRoom ( newRoom,1 ) ).thenReturn ( roomModel );


        roomMapperMock = Mockito.mock ( RoomMapper.class );
        Mockito.when ( roomMapperMock.addRoom ( roomModel ) ).thenReturn ( roomModel );
        Mockito.when ( roomMapperMock.deleteRoom ( 1 ) ).thenReturn ( true );
        Mockito.when ( roomMapperMock.deleteRoom ( longThat ( l->l!=1 ) ) ).thenThrow ( new RuntimeException ());
        Mockito.when ( roomMapperMock.findByHotelId ( 1 ) ).thenReturn ( expectedModelsList );
        Mockito.when ( roomMapperMock.findByHotelIdAndDate ( 1,exactDate,exactDate )).thenReturn ( expectedModelsList );
        Mockito.doNothing ( ).when ( roomMapperMock ).updateRoom ( roomModelUpdate );
        roomService = new RoomServiceImpl ( roomStructMock, roomMapperMock ) ;
    }

    @Test
    void findingRoomsTest(){
        Assertions.assertEquals ( roomService.getRoomsByHotelId (1) ,expectedList);
        Assertions.assertEquals ( roomService.getRoomsByHotelIdAndDate ( 1,exactDate,exactDate) ,expectedList);
    }
    @Test
    void RoomsAddTest(){
        Assertions.assertEquals ( roomService.addRoom ( newRoom,1 ),room);
    }
    @Test
    void BookingRoomTest(){
        Assertions.assertNotNull ( roomService.bookRoom ( 1,"sas" ,exactDate,exactDate));
    }
    @Test
    void UpdatingRoomTest(){
        Assertions.assertEquals ( roomService.getRoomsByHotelId (1).get ( 0 ).getDescription () ,"room");
        Assertions.assertNotNull ( roomService.updateRoom ( 1,newRoomUpdate).getDescription (),"updated");
    }

}

