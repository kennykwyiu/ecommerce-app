package finalproject.EcommerceApp.repository;

import finalproject.EcommerceApp.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends AbstractBaseRepository<Product, Long> {

}
