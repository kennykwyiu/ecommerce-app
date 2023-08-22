package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.model.ProductCategoryImages;
import finalproject.EcommerceApp.repository.ProductCategoryImagesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductCategoryImagesService extends AbstractBaseService<ProductCategoryImages, Long> {

    private ProductCategoryImagesRepository repository;

    public ProductCategoryImagesService(ProductCategoryImagesRepository repository) {
        super(repository);
        this.repository = repository;

    }
}
