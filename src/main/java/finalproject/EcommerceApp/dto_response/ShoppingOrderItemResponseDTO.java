package finalproject.EcommerceApp.dto_response;

import finalproject.EcommerceApp.dto_response.ProductResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingOrderItemResponseDTO {
    private ProductResponseDTO productResponseDTO;
    private Integer quantity;
}
