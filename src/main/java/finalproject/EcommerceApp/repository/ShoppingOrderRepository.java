package finalproject.EcommerceApp.repository;

import finalproject.EcommerceApp.model.AbstractPersistableEntity;
import finalproject.EcommerceApp.model.ShoppingOrder;
import finalproject.EcommerceApp.model.SystemUser;

import java.util.List;

public interface ShoppingOrderRepository extends AbstractBaseRepository<ShoppingOrder, Long> {
    List<ShoppingOrder> findAllBySystemUser(SystemUser systemUser);
}
