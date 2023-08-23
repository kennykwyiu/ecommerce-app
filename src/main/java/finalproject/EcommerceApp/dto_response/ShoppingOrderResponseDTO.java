package finalproject.EcommerceApp.dto_response;

import finalproject.EcommerceApp.model.ShoppingOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingOrderResponseDTO {
    private List<ShoppingOrderItemResponseDTO> shoppingOrderItemResponseDTOS;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private ShoppingOrderStatus orderStatus;
    private SystemUserAddressResponseDTO systemUserAddressResponseDTO;

}
