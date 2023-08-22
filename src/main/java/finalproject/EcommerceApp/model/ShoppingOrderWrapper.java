package finalproject.EcommerceApp.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShoppingOrderWrapper {
    private ShoppingOrder shoppingOrder;
    private List<ShoppingOrderItem> shoppingOrderItems;
}
