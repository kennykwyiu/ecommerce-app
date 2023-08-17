package finalproject.EcommerceApp.controller;

import finalproject.EcommerceApp.model.Permission;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AdminGrandRequestDTO {

    @NotBlank
    private String uid;

    private List<Permission> permissions;
}
