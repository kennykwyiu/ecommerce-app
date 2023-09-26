package finalproject.EcommerceApp.factory;

import finalproject.EcommerceApp.dto_request.SystemUserAddressRequestDTO;
import finalproject.EcommerceApp.dto_response.SystemUserAddressResponseDTO;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.model.SystemUserAddress;
import finalproject.EcommerceApp.service.SystemUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SystemUserAddressFactory {
    public SystemUserAddressResponseDTO toResponseDTO(SystemUserAddressRequestDTO requestDTO,
                                                      SystemUserAddress systemUserAddress) {
        return SystemUserAddressResponseDTO.builder()
                .id(systemUserAddress.getId())
                .room(requestDTO.getRoom())
                .flat(requestDTO.getFlat())
                .block(requestDTO.getBlock())
                .building(requestDTO.getBuilding())
                .town(requestDTO.getTown())
                .district(requestDTO.getDistrict())
                .county(requestDTO.getCounty())
                .phoneNumber(requestDTO.getPhoneNumber())
                .receiver(requestDTO.getReceiver())
                .systemUserId(systemUserAddress.getSystemUser().getId())
                .build();
    }

    public SystemUserAddressResponseDTO toResponseDTO(SystemUserAddress systemUserAddress) {
        SystemUser systemUser = systemUserAddress.getSystemUser();

        return SystemUserAddressResponseDTO.builder()
                .id(systemUserAddress.getId())
                .room(systemUserAddress.getRoom())
                .flat(systemUserAddress.getFlat())
                .block(systemUserAddress.getBlock())
                .building(systemUserAddress.getBuilding())
                .town(systemUserAddress.getTown())
                .district(systemUserAddress.getDistrict())
                .county(systemUserAddress.getCounty())
                .phoneNumber(systemUserAddress.getPhoneNumber())
                .receiver(systemUserAddress.getReceiver())
                .systemUserId(systemUser.getId())
                .activeAddress(systemUserAddress.getActiveAddress())
                .build();
    }

    public SystemUserAddress toEntity(SystemUserAddressRequestDTO requestDTO,
                                      SystemUser systemUser) {
        return SystemUserAddress.builder()
                .room(requestDTO.getRoom())
                .flat(requestDTO.getFlat())
                .block(requestDTO.getBlock())
                .building(requestDTO.getBuilding())
                .town(requestDTO.getTown())
                .district(requestDTO.getDistrict())
                .county(requestDTO.getCounty())
                .phoneNumber(requestDTO.getPhoneNumber())
                .receiver(requestDTO.getReceiver())
                .systemUser(systemUser)
                .build();
    }

}
