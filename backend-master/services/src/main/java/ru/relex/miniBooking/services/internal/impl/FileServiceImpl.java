package ru.relex.miniBooking.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.miniBooking.bd.mapper.FileMapper;
import ru.relex.miniBooking.commons.model.ImageModel;
import ru.relex.miniBooking.services.internal.FileService;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    private final FileMapper fileMapper;

    @Autowired
    public FileServiceImpl ( FileMapper fileMapper ) {
        this.fileMapper = fileMapper;
    }

    @Override
    public List<ImageModel> getImages ( long hotelId ) {
        return this.fileMapper.getImages ( hotelId );
    }

    @Override
    public void saveFile ( String fileName, long hotelId ) {
        this.fileMapper.saveFile ( fileName, hotelId );
    }

    @Override
    public ImageModel getImage(long hotelId) {
        return this.fileMapper.getImage ( hotelId );
    }
}
