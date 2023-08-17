package finalproject.EcommerceApp.dto_respond;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
