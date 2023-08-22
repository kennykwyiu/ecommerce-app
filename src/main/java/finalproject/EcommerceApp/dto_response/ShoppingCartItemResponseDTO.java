package finalproject.EcommerceApp.dto_response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ShoppingCartItemResponseDTO {

    private ProductResponseDTO product;
    private Integer quantity;


}
