package finalproject.EcommerceApp.controller.UserProduct;

import finalproject.EcommerceApp.dto_request.SystemUserAddressRequestDTO;
import finalproject.EcommerceApp.dto_response.EmptyResponseDTO;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.exception.UnauthorizedOperationException;
import finalproject.EcommerceApp.factory.SystemUserAddressFactory;
import finalproject.EcommerceApp.dto_response.SystemUserAddressResponseDTO;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.model.SystemUserAddress;
import finalproject.EcommerceApp.repository.SystemUserAddressRepository;
import finalproject.EcommerceApp.service.SystemUserAddressService;
import finalproject.EcommerceApp.service.SystemUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users/address")
public class UserAddressController {
    @Autowired
    private SystemUserAddressService systemUserAddressService;

    @Autowired
    private SystemUserAddressFactory systemUserAddressFactory;

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SystemUserAddressRepository systemUserAddressRepository;

    @PostMapping // OK
    public ResponseEntity<SystemUserAddressResponseDTO> createAddress(@Valid @RequestBody SystemUserAddressRequestDTO requestDTO,
                                                                      SystemUser systemUser) {
        SystemUserAddressResponseDTO systemUserAddressResponseDTO
                = systemUserAddressService.createAddress(requestDTO, systemUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(systemUserAddressResponseDTO);
    }

    @GetMapping // OK
    public ResponseEntity<List<SystemUserAddressResponseDTO>> getAllAddressBySystemUser(SystemUser systemUser) throws ResourceNotFoundException {
        List<SystemUserAddress> systemUserAddressList = systemUserAddressService.findBySystemUser(systemUser);

        List<SystemUserAddressResponseDTO> systemUserAddressResponseDTOList = systemUserAddressList.stream()
                .map(systemUserAddressFactory::toResponseDTO)
                .toList();
        return ResponseEntity.ok(systemUserAddressResponseDTOList);
    }

    @PutMapping("/{addressId}") // OK
    public ResponseEntity<SystemUserAddressResponseDTO> updateAddress(@PathVariable(name = "addressId") Long addressId,
                                                                      @Valid @RequestBody SystemUserAddressRequestDTO systemUserAddressRequestDTO,
                                                                      SystemUser systemUser) throws ResourceNotFoundException, UnauthorizedOperationException {

        SystemUserAddress previousAddress = systemUserAddressService.findById(addressId);
        SystemUserAddressResponseDTO systemUserAddressResponseDTO
                = systemUserAddressService.updateAddress(systemUserAddressRequestDTO,
                previousAddress,
                systemUser);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(systemUserAddressResponseDTO);
    }

    @PatchMapping("/{addressId}")
    public ResponseEntity<SystemUserAddressResponseDTO> setActiveAddress(@PathVariable(name = "addressId") Long addressId,
                                                                         SystemUser systemUser) throws ResourceNotFoundException, UnauthorizedOperationException {
        SystemUserAddress systemUserAddress = systemUserAddressService.setActiveAddress(addressId, systemUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(systemUserAddressFactory.toResponseDTO(systemUserAddress));
    }

    @DeleteMapping("/{addressId}") // OK
    public ResponseEntity<EmptyResponseDTO> deleteAddress(@PathVariable(name = "addressId") Long addressId,
                                                          SystemUser systemUser) throws ResourceNotFoundException {
        SystemUserAddress systemUserAddress = systemUserAddressService.findById(addressId);
        systemUserAddressService.delete(systemUserAddress);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
