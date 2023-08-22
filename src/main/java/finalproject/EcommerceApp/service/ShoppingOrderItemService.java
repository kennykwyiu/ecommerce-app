package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.model.ShoppingOrder;
import finalproject.EcommerceApp.model.ShoppingOrderItem;
import finalproject.EcommerceApp.repository.AbstractBaseRepository;
import finalproject.EcommerceApp.repository.ShoppingOrderItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ShoppingOrderItemService extends AbstractBaseService<ShoppingOrderItem, Long> {

    private final ShoppingOrderItemRepository repository;
    public ShoppingOrderItemService(ShoppingOrderItemRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<ShoppingOrderItem> findAllByShoppingOrder(ShoppingOrder shoppingOrder) {
        return repository.findAllByShoppingOrder(shoppingOrder);
     }
}
