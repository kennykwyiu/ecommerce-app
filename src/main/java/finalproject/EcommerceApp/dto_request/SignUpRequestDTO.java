package finalproject.EcommerceApp.dto_request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequestDTO {
    @NotBlank
    private String userName;

    @NotBlank
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @Min(8)
    @NotBlank
    private String password;


}
