package ru.relex.miniBooking.services.facade;

import ru.relex.miniBooking.commons.model.ListModel;
import ru.relex.miniBooking.services.model.hotel.Hotel;
import ru.relex.miniBooking.services.model.hotel.HotelDetail;
import ru.relex.miniBooking.services.model.hotel.NewHotel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

public interface HotelFacade {
    Hotel addHotel ( @Valid NewHotel hotel, @Positive long creatorId );

    ListModel<Hotel> getByCountryAndCity ( @NotNull String country, @NotNull String city ,long offset, long quantity);

    ListModel<Hotel> getAll ( long offset, long quantity );

    HotelDetail getById ( long id );

    boolean deleteById ( long id );

    Hotel updateHotel ( long id, @Valid NewHotel updatedHotel );

    ListModel<Hotel> getByDatesAndLocation ( @NotNull String country, @NotNull String city, @NotNull Date arrivalDate, @NotNull Date departureDate,long offset, long quantity );
    ListModel<Hotel> getByUser ( @NotNull String username  );


}
