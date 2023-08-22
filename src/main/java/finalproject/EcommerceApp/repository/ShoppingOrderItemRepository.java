package finalproject.EcommerceApp.repository;

import finalproject.EcommerceApp.model.ShoppingOrder;
import finalproject.EcommerceApp.model.ShoppingOrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShoppingOrderItemRepository extends AbstractBaseRepository<ShoppingOrderItem, Long> {
    List<ShoppingOrderItem> findAllByShoppingOrder(ShoppingOrder shoppingOrder);

}
