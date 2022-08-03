package lk.easy.rental.controller;

import lk.easy.rental.dto.ImageDTO;
import lk.easy.rental.dto.VehicleDTO;
import lk.easy.rental.service.VehicleService;
import lk.easy.rental.util.FileDownloadUtil;
import lk.easy.rental.util.FileUploadUtil;
import lk.easy.rental.util.ResponseUtil;
import lk.easy.rental.util.SearchFile;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/5/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@RestController
@RequestMapping("api/v1/vehicle")
@CrossOrigin
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;

    @Autowired
    private SearchFile searchFile;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveVehicle(@RequestBody VehicleDTO vehicleDTO){
        vehicleService.saveVehicle(vehicleDTO);
        return new ResponseUtil(200,"Saved",null);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllVehicle(){
        return new ResponseUtil(201,"OK",vehicleService.getAllVehicle());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateVehicle(@RequestBody VehicleDTO vehicleDTO){
        vehicleService.updateVehicle(vehicleDTO);
        return new ResponseUtil(200,"Updated",null);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchVehicle(@PathVariable String id){
        VehicleDTO vehicleDTO = vehicleService.searchVehicle(id);
        return new ResponseUtil(200,"Loaded", vehicleDTO);
    }

    @DeleteMapping(params = {"vehicleId"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteVehicle(@RequestParam String vehicleId){
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseUtil(200,"Deleted",null);
    }

    @GetMapping(params = {"test"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generateVehicleIds(@RequestParam String test) {
        return new ResponseUtil(200, "Ok", vehicleService.generateVehicleIds());
    }

    @SneakyThrows
    @PostMapping(path = "addCarImage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil addCarImage(@RequestParam(value = "param") MultipartFile[] multipartFile, @RequestParam("vehicleId") String vehicleId) {

        String pathDirectory = "F:\\Project\\Web-Second Sem\\Spring Projects\\Easy Car Rental System\\Car_Rental_Backend\\src\\main\\resources\\static\\image\\carImage\\";

        String[] carImageView = {"Front", "Back", "Side", "Interior"};

        for (int i = 0; i < multipartFile.length; i++) {
            String[] split = multipartFile[i].getContentType().split("/");
            if (split[1].equals("jpeg") || split[1].equals("png")) {
                String imageName = vehicleId + carImageView[i] + ".jpeg";
                /*Files.copy(multipartFile[i].getInputStream(), Paths.get(pathDirectory + File.separator + imageName), StandardCopyOption.REPLACE_EXISTING);*/
                fileUploadUtil.saveFile(pathDirectory+imageName , multipartFile[i]);
            } else {
                return new ResponseUtil(404, "please..  must be Car images type  jpeg or png", null);
            }
        }
        return new ResponseUtil(200, "Car images added complete", null);
    }

    @GetMapping(path = "getCarImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getCarImage(@RequestParam String vehicleId, String view) {

        ImageDTO imageDto = new ImageDTO(vehicleId, "car", view);
        Resource fileAsResource1 = fileDownloadUtil.getFileAsResource(imageDto);

        if (fileAsResource1==null){
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body("Car Image not found");
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fileAsResource1);
    }

    @SneakyThrows
    @PostMapping(path = "updateCarImage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateCarImage(@RequestParam(value = "carImage") MultipartFile multipartFile, @RequestParam("vehicleId") String vehicleId, @RequestParam("view") String view) {

        String pathDirectory = "F:\\Project\\Web-Second Sem\\Spring Projects\\Easy Car Rental System\\Car_Rental_Backend\\src\\main\\resources\\static\\image\\carImage\\";

        if (searchFile.searchFile(pathDirectory, vehicleId + view + ".jpeg")) {
            Files.copy(multipartFile.getInputStream(), Paths.get(pathDirectory + File.separator + vehicleId + view + ".jpeg"), StandardCopyOption.REPLACE_EXISTING);
            return new ResponseUtil(200, "car Image Updated", null);
        }
        return new ResponseUtil(200, "car Image Update Fail", null);
    }

    @SneakyThrows
    @DeleteMapping(path = "deleteCarImage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteCarAllImages(@RequestParam String vehicleId) {
        String pathDirectory = "F:\\Project\\Web-Second Sem\\Spring Projects\\Easy Car Rental System\\Car_Rental_Backend\\src\\main\\resources\\static\\image\\carImage\\";
        String[] carImageView = {"Front", "Back", "Side", "Interior"};

        for (int i = 0; i < carImageView.length; i++) {
            Files.deleteIfExists(Paths.get(pathDirectory + File.separator + vehicleId + carImageView[i] + ".jpeg"));
        }
        return new ResponseUtil(200, "car Delete success", null);
    }

    @GetMapping(params = {"brand","type"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllGeneralVehicle(@RequestParam String brand,@RequestParam String type) {
        return new ResponseUtil(200, "Ok", vehicleService.brandAndTypeCount(brand,type));
    }
}
