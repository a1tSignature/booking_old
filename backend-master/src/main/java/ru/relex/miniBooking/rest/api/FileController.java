package ru.relex.miniBooking.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.relex.miniBooking.commons.model.ImageModel;
import ru.relex.miniBooking.rest.util.FileSaver;
import ru.relex.miniBooking.services.facade.FileFacade;

import java.util.List;

@RestController
@RequestMapping("/image")
public class FileController {
    private final FileFacade fileFacade;
    private final FileSaver fileSaver;


    @Autowired
    public FileController ( FileFacade fileFacade, FileSaver fileSaver ) {
        this.fileFacade = fileFacade;
        this.fileSaver = fileSaver;
    }

    @GetMapping("/{hotelId}")
    List<ImageModel> getImage ( @PathVariable("hotelId") long hotelId ) {
        return this.fileFacade.getImage ( hotelId );
    }

    @GetMapping("/first/{hotelId}")
    ImageModel getFirstImage ( @PathVariable("hotelId") long hotelId ) {
        return this.fileFacade.getFirstImage ( hotelId );
    }

    @PostMapping("/{hotelId}")
    long saveImages ( @RequestParam List<MultipartFile> files, @PathVariable("hotelId") long hotelId ) { for (MultipartFile file : files
        ) {

            if ( !file.isEmpty ( ) ) {
                String fileName = fileSaver.save ( file );
                fileFacade.saveFile ( fileName, hotelId );
            }
        }
        return  hotelId;
    }

}
