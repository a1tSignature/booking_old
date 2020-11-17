package ru.relex.miniBooking.services.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.relex.miniBooking.bd.model.HotelDetailModel;
import ru.relex.miniBooking.bd.model.HotelModel;
import ru.relex.miniBooking.services.model.Location;
import ru.relex.miniBooking.services.model.hotel.Hotel;
import ru.relex.miniBooking.services.model.hotel.HotelDetail;
import ru.relex.miniBooking.services.model.hotel.NewHotel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-14T17:51:22+0300",
    comments = "version: 1.4.0.Beta2, compiler: IncrementalProcessingEnvironment from gradle-language-java-4.10.3.jar, environment: Java 11.0.5 (JetBrains s.r.o)"
)
@Component
public class HotelStructImpl implements HotelStruct {

    @Override
    public HotelModel createHotel(NewHotel hotel) {
        if ( hotel == null ) {
            return null;
        }

        HotelModel hotelModel = new HotelModel();

        hotelModel.setName( hotel.getName() );
        hotelModel.setCountry( hotelLocationCountry( hotel ) );
        hotelModel.setCity( hotelLocationCity( hotel ) );
        hotelModel.setStreet( hotelLocationStreet( hotel ) );
        hotelModel.setHouse( hotelLocationHouse( hotel ) );
        hotelModel.setDescription( hotel.getDescription() );
        hotelModel.setAmenities( stringListToStringArray( hotel.getAmenities() ) );

        return hotelModel;
    }

    @Override
    public Hotel fromModel(HotelModel model, long id, Date createdAt) {
        if ( model == null && createdAt == null ) {
            return null;
        }

        Hotel hotel = new Hotel();

        if ( model != null ) {
            hotel.setLocation( hotelModelToLocation( model ) );
            hotel.setName( model.getName() );
            hotel.setDescription( model.getDescription() );
            hotel.setAmenities( stringArrayToStringList( model.getAmenities() ) );
        }
        if ( createdAt != null ) {
            hotel.setCreatedAt( createdAt );
        }
        hotel.setId( id );

        return hotel;
    }

    @Override
    public Hotel fromModel(HotelModel model) {
        if ( model == null ) {
            return null;
        }

        Hotel hotel = new Hotel();

        hotel.setLocation( hotelModelToLocation1( model ) );
        hotel.setName( model.getName() );
        hotel.setDescription( model.getDescription() );
        hotel.setAmenities( stringArrayToStringList( model.getAmenities() ) );
        hotel.setId( model.getId() );
        hotel.setCreatedAt( model.getCreatedAt() );

        return hotel;
    }

    @Override
    public HotelDetail fromDetailModel(HotelDetailModel model) {
        if ( model == null ) {
            return null;
        }

        HotelDetail hotelDetail = new HotelDetail();

        hotelDetail.setLocation( hotelDetailModelToLocation( model ) );
        hotelDetail.setName( model.getName() );
        hotelDetail.setDescription( model.getDescription() );
        hotelDetail.setAmenities( stringArrayToStringList( model.getAmenities() ) );
        hotelDetail.setId( model.getId() );
        hotelDetail.setCreatedAt( model.getCreatedAt() );
        hotelDetail.setCreatorName( model.getCreator() );
        hotelDetail.setCreatorId( model.getCreatorId() );

        return hotelDetail;
    }

    private String hotelLocationCountry(NewHotel newHotel) {
        if ( newHotel == null ) {
            return null;
        }
        Location location = newHotel.getLocation();
        if ( location == null ) {
            return null;
        }
        String country = location.getCountry();
        if ( country == null ) {
            return null;
        }
        return country;
    }

    private String hotelLocationCity(NewHotel newHotel) {
        if ( newHotel == null ) {
            return null;
        }
        Location location = newHotel.getLocation();
        if ( location == null ) {
            return null;
        }
        String city = location.getCity();
        if ( city == null ) {
            return null;
        }
        return city;
    }

    private String hotelLocationStreet(NewHotel newHotel) {
        if ( newHotel == null ) {
            return null;
        }
        Location location = newHotel.getLocation();
        if ( location == null ) {
            return null;
        }
        String street = location.getStreet();
        if ( street == null ) {
            return null;
        }
        return street;
    }

    private String hotelLocationHouse(NewHotel newHotel) {
        if ( newHotel == null ) {
            return null;
        }
        Location location = newHotel.getLocation();
        if ( location == null ) {
            return null;
        }
        String house = location.getHouse();
        if ( house == null ) {
            return null;
        }
        return house;
    }

    protected String[] stringListToStringArray(List<String> list) {
        if ( list == null ) {
            return null;
        }

        String[] stringTmp = new String[list.size()];
        int i = 0;
        for ( String string : list ) {
            stringTmp[i] = string;
            i++;
        }

        return stringTmp;
    }

    protected Location hotelModelToLocation(HotelModel hotelModel) {
        if ( hotelModel == null ) {
            return null;
        }

        Location location = new Location();

        location.setCountry( hotelModel.getCountry() );
        location.setCity( hotelModel.getCity() );
        location.setStreet( hotelModel.getStreet() );
        location.setHouse( hotelModel.getHouse() );

        return location;
    }

    protected List<String> stringArrayToStringList(String[] stringArray) {
        if ( stringArray == null ) {
            return null;
        }

        List<String> list = new ArrayList<String>( stringArray.length );
        for ( String string : stringArray ) {
            list.add( string );
        }

        return list;
    }

    protected Location hotelModelToLocation1(HotelModel hotelModel) {
        if ( hotelModel == null ) {
            return null;
        }

        Location location = new Location();

        location.setCountry( hotelModel.getCountry() );
        location.setCity( hotelModel.getCity() );
        location.setStreet( hotelModel.getStreet() );
        location.setHouse( hotelModel.getHouse() );

        return location;
    }

    protected Location hotelDetailModelToLocation(HotelDetailModel hotelDetailModel) {
        if ( hotelDetailModel == null ) {
            return null;
        }

        Location location = new Location();

        location.setCountry( hotelDetailModel.getCountry() );
        location.setCity( hotelDetailModel.getCity() );
        location.setStreet( hotelDetailModel.getStreet() );
        location.setHouse( hotelDetailModel.getHouse() );

        return location;
    }
}
