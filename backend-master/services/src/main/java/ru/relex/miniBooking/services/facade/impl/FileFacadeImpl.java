package ru.relex.miniBooking.services.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.relex.miniBooking.commons.model.ImageModel;
import ru.relex.miniBooking.services.facade.FileFacade;
import ru.relex.miniBooking.services.internal.FileService;
import ru.relex.miniBooking.services.meta.Facade;

import javax.validation.constraints.NotNull;
import java.util.List;

@Facade
public class FileFacadeImpl implements FileFacade {

    private final FileService fileService;

    @Autowired
    public FileFacadeImpl ( FileService fileService ) {
        this.fileService = fileService;
    }

    @Override
    public List<ImageModel> getImage ( long hotelId ) {
        return this.fileService.getImages ( hotelId );
    }

    @Override
    public void saveFile ( @NotNull String fileName, long hotelId ) {
        this.fileService.saveFile ( fileName, hotelId );
    }

    @Override
    public ImageModel getFirstImage(@NotNull long hotelId) {
        return this.fileService.getImage ( hotelId );
    }

}
