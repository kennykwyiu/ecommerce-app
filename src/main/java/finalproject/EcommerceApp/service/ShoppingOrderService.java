package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.model.ShoppingOrder;
import finalproject.EcommerceApp.repository.AbstractBaseRepository;
import finalproject.EcommerceApp.repository.ShoppingOrderRepository;
import finalproject.EcommerceApp.service.AbstractBaseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ShoppingOrderService extends AbstractBaseService<ShoppingOrder, Long> {
    private final ShoppingOrderRepository repository;
    public ShoppingOrderService(ShoppingOrderRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
