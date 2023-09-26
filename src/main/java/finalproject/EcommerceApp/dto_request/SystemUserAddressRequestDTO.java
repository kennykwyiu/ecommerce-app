package finalproject.EcommerceApp.dto_request;

import finalproject.EcommerceApp.model.SystemUser;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class SystemUserAddressRequestDTO {
    @NotBlank
    private String room;
    @NotBlank
    private String flat;
    @NotBlank
    private String block;
    @NotBlank
    private String building;
    @NotBlank
    private String town;
    @NotBlank
    private String district;
    @NotBlank
    private String county;
    @NotBlank
    private String receiver;
    @NotBlank
    private String phoneNumber;
    @NotNull
    private Long systemUserId;

}
