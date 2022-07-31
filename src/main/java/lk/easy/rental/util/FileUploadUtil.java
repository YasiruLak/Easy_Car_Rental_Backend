package lk.easy.rental.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/31/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@Component
public class FileUploadUtil {
    public static void saveFile(String fullPathWithFileName , MultipartFile multipartFile){
        Path uploadDirectory = Paths.get(fullPathWithFileName);

        InputStream inputStream= null;
        try {
            inputStream = multipartFile.getInputStream();

            Files.copy(inputStream,uploadDirectory, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
