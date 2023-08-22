package finalproject.EcommerceApp.dto_request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ShoppingCartRequestDTO {

    private List<ShoppingCartItemRequestDTO> cartItemRequestDTOS;

    public ShoppingCartRequestDTO() {
        this.cartItemRequestDTOS = new ArrayList<>();
    }
}
