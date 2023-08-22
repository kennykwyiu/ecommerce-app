package finalproject.EcommerceApp.dto_response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoricalOrdersDTO {
    List<ShoppingOrderResponseDTO> shoppingOrderResponseDTOList;
    private BigDecimal total;
    private String orderTime;
}
