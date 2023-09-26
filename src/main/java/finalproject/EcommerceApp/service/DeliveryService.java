package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.dto_request.ShoppingOrderWrapper;
import finalproject.EcommerceApp.model.ShoppingOrderItem;
import finalproject.EcommerceApp.model.SystemUserAddress;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

import static finalproject.EcommerceApp.model.ShoppingOrderStatus.SHIPPED;

@Service
@Transactional
public class DeliveryService {

    public void deliver(ShoppingOrderWrapper shoppingOrderWrapper,
                        SystemUserAddress systemUserAddress) {

        StringBuilder productTitles = new StringBuilder();
        List<ShoppingOrderItem> shoppingOrderItems = shoppingOrderWrapper.getShoppingOrderItems();
        for (ShoppingOrderItem shoppingOrderItem : shoppingOrderItems) {
            productTitles.append(shoppingOrderItem.getProductSnapshot().getProduct().getTitle()).toString();
            productTitles.append(", ");
            if (!shoppingOrderItems.iterator().hasNext()) {
                productTitles.append(". ");
            }
        }

        StringBuilder addressString = new StringBuilder();
        addressString.append(systemUserAddress);

        System.out.printf("%s was delivered to %s", productTitles, addressString);
        shoppingOrderWrapper.getShoppingOrder().setStatus(SHIPPED);

    }
}
