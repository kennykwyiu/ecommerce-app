package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.model.Product;
import finalproject.EcommerceApp.model.ProductSnapshot;
import finalproject.EcommerceApp.repository.AbstractBaseRepository;
import finalproject.EcommerceApp.repository.ProductSnapShotRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductSnapShotService extends AbstractBaseService<ProductSnapshot, Long> {

    private final ProductSnapShotRepository repository;
    public ProductSnapShotService(ProductSnapShotRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<ProductSnapshot> findLatestSnapShotByProductId(Product product) throws ResourceNotFoundException {
        return repository.findLatestSnapShotByProductId(
                product.getId(),
                PageRequest.of(0, 1));
    }

}
