package finalproject.EcommerceApp.dto_request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
public class ShoppingCartItemRequestDTO {
    @NotBlank
    private Long productId;

    @NotBlank
    private Integer quantity;

}
