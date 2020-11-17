package ru.relex.miniBooking.services.facade;

import ru.relex.miniBooking.commons.model.ImageModel;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface FileFacade {
    List<ImageModel> getImage ( long hotelId );

    void saveFile ( @NotNull String fileName, long hotelId );

    ImageModel getFirstImage(@NotNull long hotelId);
}
