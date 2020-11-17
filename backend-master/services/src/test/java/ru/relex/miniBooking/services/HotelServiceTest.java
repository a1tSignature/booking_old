package ru.relex.miniBooking.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.relex.miniBooking.bd.mapper.HotelMapper;
import ru.relex.miniBooking.bd.model.HotelDetailModel;
import ru.relex.miniBooking.bd.model.HotelModel;
import ru.relex.miniBooking.commons.model.ListModel;
import ru.relex.miniBooking.services.internal.HotelService;
import ru.relex.miniBooking.services.internal.impl.HotelServiceImpl;
import ru.relex.miniBooking.services.mapper.HotelStruct;
import ru.relex.miniBooking.services.model.Location;
import ru.relex.miniBooking.services.model.hotel.Hotel;
import ru.relex.miniBooking.services.model.hotel.HotelDetail;
import ru.relex.miniBooking.services.model.hotel.NewHotel;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.mockito.ArgumentMatchers.eq;


public class HotelServiceTest {

    private static HotelStruct hotelStructMock;
    private static HotelMapper hotelMapperMock;
    private static HotelService hotelService;

    private final long CREATOR_ID = 1;
    private static LocalDate date = LocalDate.parse ( "9999-12-31" );
    private static Date exactDate = Date.from ( date.atStartOfDay ( ZoneId.of ( "Europe/Paris" ) ).toInstant ( ) );
    private static Location location = new Location ( "russia", "voronezh", "mira", "21d" );
    private static NewHotel newHotel1 = new NewHotel ( "11", location,
            "Zastava Hotel is located in Voronezh, 2.1 km from Central City Stadium and 3.3 km from Sunny Paradise Shopping Center. Offering city views, air-conditioned rooms have a desk and free Wi-F",
            Arrays.asList ( "water", "bath", "wifi" ) );
    private static NewHotel newHotel2 = new NewHotel ( "zastava", location,
            "Zastava Hotel is located in Voronezh, 2.1 km from Central City Stadium and 3.3 km from Sunny Paradise Shopping Center. Offering city views, air-conditioned rooms have a desk and free Wi-F",
            Arrays.asList ( "water", "bath", "wifi" ) );
    private static HotelModel hotel1 = new HotelModel ( 1, "11", location.getCountry ( ), location.getCity ( ), location.getStreet ( ), location.getHouse ( ),
            "Zastava Hotel is located in Voronezh, 2.1 km from Central City Stadium and 3.3 km from Sunny Paradise Shopping Center. Offering city views, air-conditioned rooms have a desk and free Wi-F",
            new String[]{"water", "bath", "wifi"}, exactDate );
    private static HotelModel hotel2 = new HotelModel ( 2, "zastava", location.getCountry ( ), location.getCity ( ), location.getStreet ( ), location.getHouse ( ),
            "Zastava Hotel is located in Voronezh, 2.1 km from Central City Stadium and 3.3 km from Sunny Paradise Shopping Center. Offering city views, air-conditioned rooms have a desk and free Wi-F",
            new String[]{"water", "bath", "wifi"}, exactDate );

    private static HotelDetailModel hotelDetail1 = new HotelDetailModel ( 1, "11", location.getCountry ( ), location.getCity ( ), location.getStreet ( ), location.getHouse ( ),
            "Zastava Hotel is located in Voronezh, 2.1 km from Central City Stadium and 3.3 km from Sunny Paradise Shopping Center. Offering city views, air-conditioned rooms have a desk and free Wi-F",
            new String[]{"water", "bath", "wifi"}, exactDate,"user",1 );
    private static HotelDetailModel hotelDetail2 = new HotelDetailModel ( 2, "zastava", location.getCountry ( ), location.getCity ( ), location.getStreet ( ), location.getHouse ( ),
            "Zastava Hotel is located in Voronezh, 2.1 km from Central City Stadium and 3.3 km from Sunny Paradise Shopping Center. Offering city views, air-conditioned rooms have a desk and free Wi-F",
            new String[]{"water", "bath", "wifi"}, exactDate,"user",1 );
    private static HotelDetail hotelDet1 = new HotelDetail ( 1, "11", location,
            "Zastava Hotel is located in Voronezh, 2.1 km from Central City Stadium and 3.3 km from Sunny Paradise Shopping Center. Offering city views, air-conditioned rooms have a desk and free Wi-F",
            Arrays.asList ( "water", "bath", "wifi" ), exactDate,"user",1 );
    private static HotelDetail hotelDet2 = new HotelDetail ( 2, "zastava", location,
            "Zastava Hotel is located in Voronezh, 2.1 km from Central City Stadium and 3.3 km from Sunny Paradise Shopping Center. Offering city views, air-conditioned rooms have a desk and free Wi-F",
            Arrays.asList ( "water", "bath", "wifi" ), exactDate,"user",1 );
    private static Hotel existingHotel1 = new Hotel ( 1, "11", location,
            "Zastava Hotel is located in Voronezh, 2.1 km from Central City Stadium and 3.3 km from Sunny Paradise Shopping Center. Offering city views, air-conditioned rooms have a desk and free Wi-F",
            Arrays.asList ( "water", "bath", "wifi" ), exactDate );
    private static Hotel existingHotel2 = new Hotel ( 2, "zastava", location,
            "Zastava Hotel is located in Voronezh, 2.1 km from Central City Stadium and 3.3 km from Sunny Paradise Shopping Center. Offering city views, air-conditioned rooms have a desk and free Wi-F",
            Arrays.asList ( "water", "bath", "wifi" ), exactDate );
    private static ListModel<Hotel> expectedHotelListModel = new ListModel<> ( Arrays.asList ( existingHotel1 ), 1 );
    private static ListModel<HotelModel> expectedHotelModelListModel = new ListModel<> ( Arrays.asList ( hotel1 ), 1 );


    @BeforeEach
    void cleanUp ( ) {
        hotelStructMock = Mockito.mock ( HotelStruct.class );
        Mockito.when ( hotelStructMock.createHotel ( eq ( newHotel1 ) ) ).thenReturn ( hotel1 );
        Mockito.when ( hotelStructMock.createHotel ( eq ( newHotel2 ) ) ).thenReturn ( hotel2 );
        Mockito.when ( hotelStructMock.fromModel ( hotel1, 1, exactDate ) ).thenReturn ( existingHotel1 );
        Mockito.when ( hotelStructMock.fromModel ( hotel2, 2, exactDate ) ).thenReturn ( existingHotel2 );
        Mockito.when ( hotelStructMock.fromModel ( hotel1 ) ).thenReturn ( existingHotel1 );
        Mockito.when ( hotelStructMock.fromModel ( hotel2 ) ).thenReturn ( existingHotel2 );
        Mockito.when ( hotelStructMock.fromDetailModel ( hotelDetail1 ) ).thenReturn ( hotelDet1 );
        Mockito.when ( hotelStructMock.fromDetailModel ( hotelDetail2 ) ).thenReturn ( hotelDet2);

        hotelMapperMock = Mockito.mock ( HotelMapper.class );
        Mockito.when ( hotelMapperMock.createHotel ( hotel1 ) ).thenReturn ( hotel1 );
        Mockito.when ( hotelMapperMock.createHotel ( hotel2 ) ).thenReturn ( hotel2 );
        Mockito.doNothing ( ).when ( hotelMapperMock ).addCreatedBy ( hotel1.getId ( ), CREATOR_ID );
        Mockito.doNothing ( ).when ( hotelMapperMock ).addCreatedBy ( hotel2.getId ( ), CREATOR_ID );
        Mockito.when ( hotelMapperMock.findByLocation ( existingHotel1.getLocation ( ).getCountry ( ), existingHotel1.getLocation ( ).getCity ( ),0,100 ) ).thenReturn ( expectedHotelModelListModel );
        Mockito.when ( hotelMapperMock.findById ( 1 ) ).thenReturn ( hotelDetail1 );
        Mockito.when ( hotelMapperMock.findById ( 2 ) ).thenReturn ( hotelDetail2 );
        Mockito.when ( hotelMapperMock.deleteHotel ( 1 ) ).thenReturn ( true );
        Mockito.when ( hotelMapperMock.deleteHotel ( 2 ) ).thenReturn ( true );
        hotelService = new HotelServiceImpl ( hotelStructMock, hotelMapperMock );

    }

    @Test
    void checkFindingHotel ( ) {
        hotelService.addHotel ( newHotel1, CREATOR_ID );
        Assertions.assertEquals ( hotelService.getById ( 1 ).getId (), existingHotel1.getId () );
    }

    @Test
    void checkHotelInfoUpdate ( ) {
        var updatedHotel = hotelService.updateHotel ( 1, newHotel2 );
        var expectedHotel = existingHotel2;
        existingHotel2.setId ( 1 );
        Assertions.assertEquals ( updatedHotel.getDescription (), existingHotel2.getDescription () );


    }

    @Test
    void hotelSearchTest ( ) {
        Assertions.assertEquals ( hotelService.getByCountryAndCity ( existingHotel1.getLocation ( ).getCountry ( ), existingHotel1.getLocation ( ).getCity ( ), 0, 100 ).getAmount (), expectedHotelListModel.getAmount () );
        Assertions.assertEquals ( hotelService.getByCountryAndCity ( existingHotel1.getLocation ( ).getCountry ( ), existingHotel1.getLocation ( ).getCity ( ), 0, 100 ).getList (), expectedHotelListModel.getList () );

    }

    @Test
    void hotelDeleteTest ( ) {
        Assertions.assertEquals ( hotelService.deleteById ( 1 ), true );
        Assertions.assertEquals ( hotelService.deleteById ( 2 ), true );


    }


}

