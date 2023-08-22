package finalproject.EcommerceApp.repository;

import finalproject.EcommerceApp.model.ProductCategoryImages;
import finalproject.EcommerceApp.model.ProductImages;
import finalproject.EcommerceApp.repository.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryImagesRepository extends AbstractBaseRepository<ProductCategoryImages, Long> {
}
