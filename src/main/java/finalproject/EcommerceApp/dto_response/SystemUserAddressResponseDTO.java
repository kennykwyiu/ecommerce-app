package finalproject.EcommerceApp.dto_response;

import finalproject.EcommerceApp.model.SystemUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SystemUserAddressResponseDTO {
    private Long id;
    private String room;
    private String flat;
    private String block;
    private String building;
    private String town;
    private String district;
    private String county;
    private String phoneNumber;
    private String receiver;
    private Byte activeAddress;
    private Long systemUserId;

}
