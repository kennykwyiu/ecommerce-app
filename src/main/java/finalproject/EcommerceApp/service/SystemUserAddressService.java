package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.dto_request.SystemUserAddressRequestDTO;
import finalproject.EcommerceApp.factory.SystemUserAddressFactory;
import finalproject.EcommerceApp.dto_response.SystemUserAddressResponseDTO;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.model.SystemUserAddress;
import finalproject.EcommerceApp.repository.SystemUserAddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SystemUserAddressService extends AbstractBaseService<SystemUserAddress, Long> {

    @Autowired
    private SystemUserAddressFactory systemUserAddressFactory;
    private final SystemUserAddressRepository repository;

    public SystemUserAddressService(SystemUserAddressRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public SystemUserAddress findBySystemUser(SystemUser systemUser) {
        return repository.findBySystemUser(systemUser);
    }

    public SystemUserAddressResponseDTO updateAddress(SystemUserAddressRequestDTO systemUserAddressRequestDTO,
                                                                      SystemUserAddress previousAddress,
                                                                      SystemUser systemUser) {
        previousAddress.setRoom(systemUserAddressRequestDTO.getRoom());
        previousAddress.setFlat(systemUserAddressRequestDTO.getFlat());
        previousAddress.setBlock(systemUserAddressRequestDTO.getBlock());
        previousAddress.setBuilding(systemUserAddressRequestDTO.getBuilding());
        previousAddress.setTown(systemUserAddressRequestDTO.getTown());
        previousAddress.setDistrict(systemUserAddressRequestDTO.getDistrict());
        previousAddress.setCounty(systemUserAddressRequestDTO.getCounty());
        previousAddress.setReceiver(systemUserAddressRequestDTO.getReceiver());
        previousAddress.setPhoneNumber(systemUserAddressRequestDTO.getPhoneNumber());
        repository.save(previousAddress);

        return systemUserAddressFactory.toResponseDTO(systemUserAddressRequestDTO, systemUser);
    }
}
