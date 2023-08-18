package finalproject.EcommerceApp.dto_response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SystemUserRespondDTO {
    private Long id;

    private String userName;

    private String fullName;

    private String email;

    private String externalUserId;


}
