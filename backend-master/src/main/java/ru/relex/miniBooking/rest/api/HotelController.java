package ru.relex.miniBooking.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.relex.miniBooking.commons.model.ListModel;
import ru.relex.miniBooking.rest.exception.NoSuchObjectException;
import ru.relex.miniBooking.rest.util.IdExtractor;
import ru.relex.miniBooking.services.facade.HotelFacade;
import ru.relex.miniBooking.services.model.hotel.Hotel;
import ru.relex.miniBooking.services.model.hotel.HotelDetail;
import ru.relex.miniBooking.services.model.hotel.NewHotel;

import java.util.Date;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    private HotelFacade hotelFacade;
    private IdExtractor idExtractor;

    @Autowired
    public HotelController ( HotelFacade hotelFacade, IdExtractor idExtractor ) {
        this.hotelFacade = hotelFacade;
        this.idExtractor = idExtractor;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Hotel createHotel ( @RequestBody NewHotel hotel, Authentication authentication ) {
        long creatorId = idExtractor.extract ( authentication );
        return hotelFacade.addHotel ( hotel, creatorId );

    }

    @GetMapping(path = "/{country}/{city}")
    ListModel<Hotel> getByLocation ( @PathVariable("country") String country, @PathVariable("city") String city, @RequestParam("offset") long offset, @RequestParam("quantity") long quantity ) {
       return hotelFacade.getByCountryAndCity ( country, city, offset, quantity );

    }

    @GetMapping(path = "/createdby/{username}")
    ListModel<Hotel> getByLocation ( @PathVariable("username") String username ) {
        return hotelFacade.getByUser ( username );

       }

    @PutMapping(path = "/{id}")
    Hotel updateHotel ( @PathVariable("id") long id, @RequestBody NewHotel hotel ) {
        return hotelFacade.updateHotel ( id, hotel );
    }


    @GetMapping(path = "/{country}/{city}/byDate")
    ListModel<Hotel> getByLocationAndDate (
            @PathVariable("country") String country,
            @PathVariable("city") String city,
            @RequestParam("arrivalDate") Date arrivalDate,
            @RequestParam("dateOfDeparture") Date dateOfDeparture,
            @RequestParam("offset") long offset,
            @RequestParam("quantity") long quantity
    ) {
        return hotelFacade.getByDatesAndLocation ( country, city, arrivalDate, dateOfDeparture, offset, quantity );


    }

    @GetMapping
    ListModel<Hotel> getAllHotels ( @RequestParam("offset") long offset, @RequestParam("quantity") long quantity ) {

        return hotelFacade.getAll ( offset, quantity );

    }

    @GetMapping(path = "/{id}")
    HotelDetail getById ( @PathVariable("id") long id ) {
        HotelDetail hotel = hotelFacade.getById ( id );
        if ( hotel != null )
            return hotel;
        else
            throw new NoSuchObjectException ( Hotel.class );
    }

    @DeleteMapping(path = "/{id}")
    void deleteHotel ( @PathVariable("id") long id ) {
        if ( hotelFacade.deleteById ( id ) ) {
            return;
        }
        throw new NoSuchObjectException ( Hotel.class );
    }

}
