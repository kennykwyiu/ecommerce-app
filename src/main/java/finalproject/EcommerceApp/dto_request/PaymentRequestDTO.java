package finalproject.EcommerceApp.dto_request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentRequestDTO {
    private BigDecimal orderTotal;
    private Long shoppingOrderId;

}
