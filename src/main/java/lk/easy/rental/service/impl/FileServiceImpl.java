package lk.easy.rental.service.impl;

import lk.easy.rental.dto.FileDTO;
import lk.easy.rental.enums.ReferencedType;
import lk.easy.rental.exception.DuplicateEntryException;
import lk.easy.rental.repo.FileRepo;
import lk.easy.rental.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.function.EntityResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/24/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@Service
@Transactional
public class FileServiceImpl implements FileService {

//    @Value("${app.file.upload.path}")
    private String fileUpload= "uploads";

    @Autowired
    FileRepo fileRepo;

    @Override
    public ResponseEntity<?> store(FileDTO fileDTO) throws Exception {
        if (fileDTO.getFileCategory().equals(ReferencedType.NIC)||fileDTO.getFileCategory().equals(ReferencedType.LISION)){
            if (fileRepo.existsByReferencedIdAndReferencedType(fileDTO.getReferencedId(), fileDTO.getFileCategory()))
                throw new DuplicateEntryException("File Already Exists");
        }
        String dirName = "F://Project/Web-Second Sem/Spring Projects/Easy Car Rental System/uploads/";
        String fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileDTO.getUploadFile().getOriginalFilename());
        File uploadsDir = new File( dirName);
        if (!uploadsDir.exists())
            uploadsDir.mkdir();
        String filePath = dirName.concat(fileName);

        Path path = Paths.get(filePath);
        byte[] fi = fileDTO.getUploadFile().getBytes();
        Files.write(path,fi);

        lk.easy.rental.entity.File file = new lk.easy.rental.entity.File(fileDTO.getReferencedId(),fileDTO.getFileCategory(),filePath);
        fileRepo.save(file);

        return ResponseEntity.ok().build();


    }

    @Override
    public byte[] getFile(String referencedId, ReferencedType referencedType) throws IOException {
        lk.easy.rental.entity.File file = fileRepo.findByReferencedIdAndReferencedType(referencedId, referencedType);
        if (file == null) {
            throw new DuplicateEntryException("Not Such a File");
        }
        File file1 = new File(file.getFilePath());
        FileInputStream fileInputStream = new FileInputStream(file1);
        return StreamUtils.copyToByteArray(fileInputStream);
    }
}
