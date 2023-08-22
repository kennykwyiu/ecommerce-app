package finalproject.EcommerceApp.repository;

import finalproject.EcommerceApp.model.ProductSnapshot;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSnapShotRepository extends AbstractBaseRepository<ProductSnapshot, Long> {
    @Query("select ps from ProductSnapshot ps " +
            "where ps.product.id = :productId " +
            "order by ps.createdAt desc")
    List<ProductSnapshot> findLatestSnapShotByProductId(@Param("productId") Long productId,
                                                        Pageable pageable);
}
