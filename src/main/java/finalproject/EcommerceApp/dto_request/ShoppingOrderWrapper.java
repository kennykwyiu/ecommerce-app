package finalproject.EcommerceApp.dto_request;

import finalproject.EcommerceApp.model.ShoppingOrder;
import finalproject.EcommerceApp.model.ShoppingOrderItem;
import finalproject.EcommerceApp.model.SystemUserAddress;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShoppingOrderWrapper {
    private ShoppingOrder shoppingOrder;
    private List<ShoppingOrderItem> shoppingOrderItems;
}
