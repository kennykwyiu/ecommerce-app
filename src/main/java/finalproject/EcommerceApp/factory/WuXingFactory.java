package finalproject.EcommerceApp.factory;

import finalproject.EcommerceApp.dto_request.WuXingRequestDTO;
import finalproject.EcommerceApp.dto_response.WuXingResponseDTO;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.model.WuXing;
import finalproject.EcommerceApp.service.SystemUserService;
import finalproject.EcommerceApp.service.WuXingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WuXingFactory {

    @Autowired
    private WuXingService wuXingService;
    @Autowired
    private SystemUserService systemUserService;

    public WuXingResponseDTO toResponseDTO(WuXingRequestDTO requestDTO) {
        WuXing wuXing = toEntity(requestDTO);
        WuXingResponseDTO wuXingResponseDTO = WuXingResponseDTO.builder()
                .id(wuXing.getId())
                .birthTime(wuXing.getBirthTime())
                .birthDay(wuXing.getBirthDay())
                .birthMonth(wuXing.getBirthMonth())
                .birthYear(wuXing.getBirthYear())
                .build();
        return wuXingResponseDTO;
    }
    public WuXingResponseDTO toResponseDTO(WuXing wuXing) {
        WuXingResponseDTO wuXingResponseDTO = WuXingResponseDTO.builder()
                .id(wuXing.getId())
                .birthTime(wuXing.getBirthTime())
                .birthDay(wuXing.getBirthDay())
                .birthMonth(wuXing.getBirthMonth())
                .birthYear(wuXing.getBirthYear())
                .build();
        return wuXingResponseDTO;
    }

    public WuXing toEntity(WuXingRequestDTO requestDTO) {
        SystemUser systemUser = systemUserService.getReferenceById(requestDTO.getSystemUserId());
        WuXing wuXing = WuXing.builder()
                .birthTime(requestDTO.getBirthTime())
                .birthDay(requestDTO.getBirthDay())
                .birthMonth(requestDTO.getBirthMonth())
                .birthYear(requestDTO.getBirthYear())
                .systemUser(systemUser)
                .build();
        wuXingService.save(wuXing);
        return wuXing;
    }
}
