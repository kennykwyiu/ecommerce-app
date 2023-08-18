package finalproject.EcommerceApp.factory;

import finalproject.EcommerceApp.dto_request.SignUpRequestDTO;
import finalproject.EcommerceApp.dto_response.SystemUserRespondDTO;
import finalproject.EcommerceApp.model.SystemUser;
import org.springframework.stereotype.Component;

@Component
public class SystemUserFactory {


    public SystemUserRespondDTO toDTO(SystemUser systemUser) {
        return SystemUserRespondDTO.builder()
                .id(systemUser.getId())
                .userName(systemUser.getUserName())
                .fullName(systemUser.getFullName())
                .email(systemUser.getEmail())
                .externalUserId(systemUser.getExternalUserId())
                .build();
    }

    public SystemUser toEntity(SignUpRequestDTO dto) {
        return SystemUser.builder()
                .userName(dto.getUserName())
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .build();
    }


}
