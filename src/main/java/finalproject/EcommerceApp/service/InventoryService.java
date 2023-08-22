package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.exception.InsufficientInventoryException;
import finalproject.EcommerceApp.model.ShoppingOrder;
import finalproject.EcommerceApp.model.ShoppingOrderItem;
import finalproject.EcommerceApp.model.ShoppingOrderWrapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.util.List;

import static finalproject.EcommerceApp.model.ShoppingOrderStatus.INSUFFICIENT_INVENTORY;

@Service
@Transactional
public class InventoryService {
    @Autowired
    private ShoppingOrderService shoppingOrderService;

    // TODO: may need to add additional pre-check when add the product from cart to order

    public void checkStock(ShoppingOrderWrapper shoppingOrderWrapper) throws InsufficientInventoryException {
        List<ShoppingOrderItem> shoppingOrderItems = shoppingOrderWrapper.getShoppingOrderItems();
        StringBuilder errorMessage = new StringBuilder();
        for (ShoppingOrderItem shoppingOrderItem : shoppingOrderItems) {
            if (shoppingOrderItem.getProductSnapshot().getQuantity()
                    .compareTo(shoppingOrderItem.getQuantity()) < 0) {

                errorMessage.append(
                        String.format("%s was just sold out\n",
                                shoppingOrderItem.getProductSnapshot().getTitle())
                );
            }
        }
        ShoppingOrder shoppingOrder = shoppingOrderWrapper.getShoppingOrder();
        if (!errorMessage.isEmpty()) {
            shoppingOrder.setStatus(INSUFFICIENT_INVENTORY);
            shoppingOrderService.save(shoppingOrder); // TODO: why will loop again
            throw new InsufficientInventoryException(errorMessage.toString());
        }
    }
}
