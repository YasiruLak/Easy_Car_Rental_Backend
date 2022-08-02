package lk.easy.rental.controller;

import lk.easy.rental.dto.CustomerDTO;
import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.service.SignUpService;
import lk.easy.rental.util.FileDownloadUtil;
import lk.easy.rental.util.FileUploadUtil;
import lk.easy.rental.util.ResponseUtil;
import lk.easy.rental.util.SearchFile;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/26/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@RestController
@RequestMapping("api/v1/signup")
@CrossOrigin
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    @Autowired
    private FileDownloadUtil fileDownloadUtil;

    @Autowired
    private SearchFile searchFile;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/driver")
    public ResponseUtil saveDriver(@RequestBody DriverDTO driverDTO){
        signUpService.saveDriver(driverDTO);
        return new ResponseUtil(200,"Saved",null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/customer")
    public ResponseUtil saveCustomer(@RequestBody CustomerDTO customerDTO){
        signUpService.saveCustomer(customerDTO);
        return new ResponseUtil(200,"Saved",null);
    }

    @SneakyThrows
    @PostMapping(path = "addRegisterUserImage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil addRegisterUserImage(@RequestParam(value = "param") MultipartFile[] multipartFile, @RequestParam("id") String id) {

        String pathDirectory = "F:\\Project\\Web-Second Sem\\Spring Projects\\Easy Car Rental System\\Car_Rental_Backend\\src\\main\\resources\\static\\image\\registerImage\\";

        String[] registerImageView = {"Nic", "Licence"};

        for (int i = 0; i < multipartFile.length; i++) {
            String[] split = multipartFile[i].getContentType().split("/");
            if (split[1].equals("jpeg") || split[1].equals("png")) {
                String imageName = id + registerImageView[i] + ".jpeg";
                /*Files.copy(multipartFile[i].getInputStream(), Paths.get(pathDirectory + File.separator + imageName), StandardCopyOption.REPLACE_EXISTING);*/
                fileUploadUtil.saveFile(pathDirectory+imageName , multipartFile[i]);
            } else {
                return new ResponseUtil(404, "please..  must be images type  jpeg or png", null);
            }
        }
        return new ResponseUtil(200, "User images added complete", null);
    }

}


