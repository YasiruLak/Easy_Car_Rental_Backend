package lk.easy.rental.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/31/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@Component
public class SearchFile {
    boolean b=false;
    public boolean searchFile(String path,String imageName) {
        b=false;
        try {
            Files.list(Paths.get(path)).forEach(file -> {
                if (file.getFileName().toString().startsWith(imageName)) {
                    System.out.println("file  found");
                    b = true;
                    return;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (b == true) {
            return true;

        }
        return false;
    }
}
