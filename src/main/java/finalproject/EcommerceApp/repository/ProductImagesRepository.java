package finalproject.EcommerceApp.repository;

import finalproject.EcommerceApp.model.Product;
import finalproject.EcommerceApp.model.ProductImages;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImagesRepository extends AbstractBaseRepository<ProductImages, Long> {

  @Query("select url from ProductImages pi where pi.product = :product")
  List<String> findAllByProduct(@Param("product") Product product);
}
