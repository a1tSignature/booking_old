package ru.relex.miniBooking.rest.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
@Component
public class FileSaver {
    @Value("${pathToSave}")
    private String fileDirectory;

    public String save ( MultipartFile file ) {
        System.out.println ( fileDirectory );
        String filename = file.getOriginalFilename ( );
        try {
            byte[] bytes = file.getBytes ( );
            BufferedOutputStream stream = new BufferedOutputStream ( new FileOutputStream ( new File ( fileDirectory + filename ) ) );
            stream.write(bytes);
            stream.flush();
            stream.close();
        } catch ( IOException e ) {
            return null;
        }
        return filename;
    }
}
