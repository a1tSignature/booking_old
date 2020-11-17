package ru.relex.miniBooking.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.miniBooking.bd.model.HotelDetailModel;
import ru.relex.miniBooking.bd.model.HotelModel;
import ru.relex.miniBooking.services.model.hotel.Hotel;
import ru.relex.miniBooking.services.model.hotel.HotelDetail;
import ru.relex.miniBooking.services.model.hotel.NewHotel;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface HotelStruct {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "country", source = "location.country")
    @Mapping(target = "city", source = "location.city")
    @Mapping(target = "street", source = "location.street")
    @Mapping(target = "house", source = "location.house")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "amenities", source = "amenities")
    HotelModel createHotel ( NewHotel hotel );

    @Mapping(target = "name", source = "model.name")
    @Mapping(target = "location.country", source = "model.country")
    @Mapping(target = "location.city", source = "model.city")
    @Mapping(target = "location.street", source = "model.street")
    @Mapping(target = "location.house", source = "model.house")
    @Mapping(target = "description", source = "model.description")
    @Mapping(target = "amenities", source = "model.amenities")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")
    Hotel fromModel ( HotelModel model, long id, Date createdAt );






    @Mapping(target = "name", source = "model.name")
    @Mapping(target = "location.country", source = "model.country")
    @Mapping(target = "location.city", source = "model.city")
    @Mapping(target = "location.street", source = "model.street")
    @Mapping(target = "location.house", source = "model.house")
    @Mapping(target = "description", source = "model.description")
    @Mapping(target = "amenities", source = "model.amenities")
    @Mapping(target = "id", source = "model.id")
    @Mapping(target = "createdAt", source = "model.createdAt")
    Hotel fromModel ( HotelModel model );

    @Mapping(target = "name", source = "model.name")
    @Mapping(target = "location.country", source = "model.country")
    @Mapping(target = "location.city", source = "model.city")
    @Mapping(target = "location.street", source = "model.street")
    @Mapping(target = "location.house", source = "model.house")
    @Mapping(target = "description", source = "model.description")
    @Mapping(target = "amenities", source = "model.amenities")
    @Mapping(target = "id", source = "model.id")
    @Mapping(target = "createdAt", source = "model.createdAt")
    @Mapping(target = "creatorName", source = "model.creator")
    @Mapping(target = "creatorId", source = "model.creatorId")
    HotelDetail fromDetailModel ( HotelDetailModel model );


}
