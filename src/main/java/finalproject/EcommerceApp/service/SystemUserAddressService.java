package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.dto_request.SystemUserAddressRequestDTO;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.exception.UnauthorizedOperationException;
import finalproject.EcommerceApp.factory.SystemUserAddressFactory;
import finalproject.EcommerceApp.dto_response.SystemUserAddressResponseDTO;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.model.SystemUserAddress;
import finalproject.EcommerceApp.repository.SystemUserAddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class SystemUserAddressService extends AbstractBaseService<SystemUserAddress, Long> {

    @Autowired
    private SystemUserAddressFactory systemUserAddressFactory;

    @Autowired
    private SystemUserService systemUserService;

    private final SystemUserAddressRepository repository;

    public SystemUserAddressService(SystemUserAddressRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<SystemUserAddress> findBySystemUser(SystemUser systemUser) {
        return repository.findBySystemUser(systemUser);
    }

    public SystemUserAddressResponseDTO updateAddress(SystemUserAddressRequestDTO systemUserAddressRequestDTO,
                                                      SystemUserAddress previousAddress,
                                                      SystemUser systemUser) throws ResourceNotFoundException, UnauthorizedOperationException {

        SystemUser expectedSystemUser = systemUserService.getReferenceById(systemUserAddressRequestDTO.getSystemUserId());
        ServiceUtils.validateSystemUserForEntity(expectedSystemUser, systemUser, "SystemUserAddress");

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
        SystemUserAddress systemUserAddress = getActiveSystemUserAddress(systemUser);

        return systemUserAddressFactory.toResponseDTO(systemUserAddressRequestDTO, systemUserAddress);
    }

    public SystemUserAddress setActiveAddress(Long addressId, SystemUser systemUser) throws ResourceNotFoundException, UnauthorizedOperationException {

        SystemUserAddress newActiveAddress = findById(addressId);
        SystemUser actualSystemUser = newActiveAddress.getSystemUser();
        ServiceUtils.validateSystemUserForEntity(actualSystemUser, systemUser, newActiveAddress);
        SystemUserAddress oldActiveAddress = getActiveSystemUserAddress(actualSystemUser);

        if (!Objects.isNull(oldActiveAddress)) {
            newActiveAddress.setActiveAddress((byte) 1);
            repository.save(newActiveAddress);
            oldActiveAddress.setActiveAddress((byte) 0);
            repository.save(oldActiveAddress);
        }
        newActiveAddress.setActiveAddress((byte) 1);
        repository.save(newActiveAddress);


//        if (oldActiveAddress.equals((byte) 1)) {
//            newActiveAddress.setActiveAddress((byte) 1);
//            oldActiveAddress.setActiveAddress((byte) 0);
//        }
//        newActiveAddress.setActiveAddress((byte) 1);

        return newActiveAddress;
    }

    public SystemUserAddress getActiveSystemUserAddress(SystemUser systemUser) throws ResourceNotFoundException {
        SystemUserAddress systemUserAddress = repository.findBySystemUserAndActiveAddress(systemUser.getId());
//                .orElseThrow(() -> new ResourceNotFoundException(String.format("SystemUser#%s don't have active
//                address.", systemUser.getId()))
//                );

        return systemUserAddress;
    }

    public SystemUserAddressResponseDTO createAddress(SystemUserAddressRequestDTO requestDTO,
                                                      SystemUser systemUser) {
        SystemUserAddress systemUserAddress
                = systemUserAddressFactory.toEntity(requestDTO, systemUser);
        repository.save(systemUserAddress);

        return systemUserAddressFactory.toResponseDTO(systemUserAddress);
    }
}
