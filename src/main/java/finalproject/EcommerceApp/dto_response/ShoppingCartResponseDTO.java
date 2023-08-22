package finalproject.EcommerceApp.dto_response;

import finalproject.EcommerceApp.dto_request.ShoppingCartItemRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ShoppingCartResponseDTO {

    private List<ShoppingCartItemResponseDTO> cartItemResponseDTOS;

}
