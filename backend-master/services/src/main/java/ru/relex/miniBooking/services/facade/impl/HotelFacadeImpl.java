package ru.relex.miniBooking.services.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.relex.miniBooking.commons.model.ListModel;
import ru.relex.miniBooking.services.facade.HotelFacade;
import ru.relex.miniBooking.services.internal.HotelService;
import ru.relex.miniBooking.services.meta.Facade;
import ru.relex.miniBooking.services.model.hotel.Hotel;
import ru.relex.miniBooking.services.model.hotel.HotelDetail;
import ru.relex.miniBooking.services.model.hotel.NewHotel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Date;

@Facade
public class HotelFacadeImpl implements HotelFacade {

    HotelService hotelService;

    @Autowired
    public HotelFacadeImpl ( HotelService hotelService ) {
        this.hotelService = hotelService;
    }

    @Override
    public Hotel addHotel ( NewHotel hotel, long creatorId ) {
        return hotelService.addHotel ( hotel, creatorId );
    }

    @Override
    public ListModel<Hotel> getByCountryAndCity ( @NotNull String country, @NotNull String city, long offset, long quantity ) {
        if ( country == null || city == null )
            return new ListModel<> ( Collections.emptyList ( ), 0 );
        return hotelService.getByCountryAndCity ( country, city, offset, quantity );
    }

    @Override
    public ListModel<Hotel> getAll ( long offset, long quantity ) {
        return this.hotelService.getAll ( offset, quantity );
    }

    @Override
    public HotelDetail getById ( long id ) {
        return hotelService.getById ( id );
    }

    @Override
    public boolean deleteById ( long id ) {
        return hotelService.deleteById ( id );
    }

    @Override
    public Hotel updateHotel ( long id, @Valid NewHotel updatedHotel ) {
        return hotelService.updateHotel ( id, updatedHotel );
    }

    @Override
    public ListModel<Hotel> getByDatesAndLocation ( @NotNull String country, @NotNull String city, @NotNull Date arrivalDate, @NotNull Date departureDate, long offset, long quantity ) {
        return hotelService.getByDatesAndLocation ( country, city, arrivalDate, departureDate, offset, quantity );
    }

    @Override
    public ListModel<Hotel> getByUser ( @NotNull String username ) {
        return  hotelService.getByUser (username);
    }
}
