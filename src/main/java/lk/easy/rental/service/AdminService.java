package lk.easy.rental.service;

import lk.easy.rental.dto.AdminDTO;


import java.util.List;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/5/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface AdminService {

    void saveAdmin(AdminDTO adminDTO);
    void deleteAdmin(String id);
    void updateAdmin(AdminDTO adminDTO);
    AdminDTO searchAdmin(String id);
    List<AdminDTO> getAllAdmin();
    String generateAdminIds();
    long countAdmin();
}
