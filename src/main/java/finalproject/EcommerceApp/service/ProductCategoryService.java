package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.model.Product;
import finalproject.EcommerceApp.model.ProductCategory;
import finalproject.EcommerceApp.repository.ProductCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ProductCategoryService extends AbstractBaseService<ProductCategory, Long> {

    private ProductCategoryRepository repository;

    public ProductCategoryService(ProductCategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
