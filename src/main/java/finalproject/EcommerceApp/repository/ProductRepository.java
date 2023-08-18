package finalproject.EcommerceApp.repository;

import finalproject.EcommerceApp.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends AbstractBaseRepository<Product, Long> {
    @Query("select p from Product p " +
            "where lower(p.title) like lower(concat('%', lower(:query), '%')) " +
            "or lower(p.description) like lower(concat('%', lower(:query), '%'))")
    List<Product> findAllByQuery(String query);

    @Query("select p from Product p " +
            "left join ProductCategory pc on p.productCategory.id = pc.id " +
            "where pc.id = :productCategoryId")
    List<Product> findByProductCategoryId(@Param("productCategoryId") Long productCategoryId);
}
