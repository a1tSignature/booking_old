package ru.relex.miniBooking.services.internal;

import ru.relex.miniBooking.commons.model.ImageModel;

import java.util.List;

public interface FileService {
    List<ImageModel> getImages ( long hotelId );

    void saveFile ( String fileName,long hotelId );

    ImageModel getImage(long hotelId);
}
