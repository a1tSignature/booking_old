package ru.relex.miniBooking.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.miniBooking.bd.mapper.HotelMapper;
import ru.relex.miniBooking.bd.model.HotelModel;
import ru.relex.miniBooking.commons.model.ListModel;
import ru.relex.miniBooking.services.internal.HotelService;
import ru.relex.miniBooking.services.mapper.HotelStruct;
import ru.relex.miniBooking.services.model.hotel.Hotel;
import ru.relex.miniBooking.services.model.hotel.HotelDetail;
import ru.relex.miniBooking.services.model.hotel.NewHotel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Date;

@Service
public class HotelServiceImpl implements HotelService {


    private final HotelStruct hotelStruct;
    private final HotelMapper hotelMapper;

    @Autowired
    public HotelServiceImpl(HotelStruct hotelStruct, HotelMapper hotelMapper) {
        this.hotelStruct = hotelStruct;
        this.hotelMapper = hotelMapper;
    }


    @Override
    public Hotel addHotel(NewHotel newHotel, long creatorId) {

        final var model = hotelStruct.createHotel(newHotel);
        final var newModel = hotelMapper.createHotel(model);
        final var hotel = hotelStruct.fromModel(model, newModel.getId(), newModel.getCreatedAt());
        hotelMapper.addCreatedBy(hotel.getId(), creatorId);
        return hotel;

    }

    @Override
    public ListModel<Hotel> getByCountryAndCity(@NotNull String country, @NotNull String city, long offset, long quantity) {
        ListModel<HotelModel> models = hotelMapper.findByLocation(country, city, offset, quantity);
        return getHotelListModel(models);
    }

    private ListModel<Hotel> getHotelListModel(ListModel<HotelModel> models) {
        if (models != null) {
            ListModel<Hotel> hotels = new ListModel<>();
            for (HotelModel model : models.list
            ) {
                hotels.list.add(hotelStruct.fromModel(model));
            }
            hotels.setAmount(models.getAmount());
            return hotels;
        }
        return new ListModel<>(Collections.emptyList(), 0);
    }

    @Override
    public ListModel<Hotel> getAll(long offset, long quantity) {
        ListModel<HotelModel> models = hotelMapper.findAll(offset, quantity);
        return getHotelListModel(models);
    }

    @Override
    public ListModel<Hotel> getByDatesAndLocation(@NotNull String country, @NotNull String city, @NotNull Date arrivalDate, @NotNull Date departureDate, long offset, long quantity) {
        ListModel<HotelModel> models = hotelMapper.findByLocationAndDate(country, city, arrivalDate, departureDate, offset, quantity);
        return getHotelListModel(models);
    }

    @Override
    public ListModel<Hotel> getByUser(String username) {
        ListModel<HotelModel> models = hotelMapper.findByUser(username);
        return getHotelListModel(models);
    }

    @Override
    public HotelDetail getById(long id) {
        final var found = hotelMapper.findById(id);
        if (found != null)
            found.setId(id);
        return hotelStruct.fromDetailModel(found);
    }

    @Override
    public boolean deleteById(long id) {
        //TODO:delete rooms and reserved rooms with id
        return hotelMapper.deleteHotel(id);

    }

    @Override
    public Hotel updateHotel(long id, @Valid NewHotel updatedHotel) {
        final var model = hotelStruct.createHotel(updatedHotel);
        model.setId(id);
        hotelMapper.updateHotel(model);
        return hotelStruct.fromModel(model);
    }

}
