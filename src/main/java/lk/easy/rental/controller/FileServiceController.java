package lk.easy.rental.controller;

import lk.easy.rental.dto.FileDTO;
import lk.easy.rental.enums.ReferencedType;
import lk.easy.rental.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/24/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@CrossOrigin
@RestController
@RequestMapping("api/v1/fileupload")
public class FileServiceController {

    @Autowired
    FileService fileService;

    @PostMapping(produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity saveFile(FileDTO fileDTO) throws Exception {
        return fileService.store(fileDTO);
    }

    @GetMapping("/{referencedId}/{referencedType}")
    public ResponseEntity getAllImagesFromDatabase(@PathVariable String referencedId, @PathVariable ReferencedType referencedType) throws IOException {
        byte[] file = fileService.getFile(referencedId, referencedType);
        return ResponseEntity.ok(file);
    }
}
