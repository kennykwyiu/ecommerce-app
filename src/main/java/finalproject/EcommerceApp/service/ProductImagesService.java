package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.model.Product;
import finalproject.EcommerceApp.model.ProductImages;
import finalproject.EcommerceApp.repository.ProductImagesRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ProductImagesService extends AbstractBaseService<ProductImages, Long> {
    @Autowired
    private ProductImagesRepository repository;

    public ProductImagesService(ProductImagesRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<String> getAllByProduct(Product product) {
        return repository.findAllByProductId(product);
    }

    public ResponseEntity<Void> save(String url, Product product) {
        repository.save(ProductImages.builder()
                .url(url)
                .product(product)
                .build());
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}
