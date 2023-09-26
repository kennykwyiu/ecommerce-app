package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.dto_request.ProductRequestDTO;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.factory.ProductImageFactory;
import finalproject.EcommerceApp.model.Product;
import finalproject.EcommerceApp.model.ProductCategory;
import finalproject.EcommerceApp.model.ProductImages;
import finalproject.EcommerceApp.repository.ProductImagesRepository;
import finalproject.EcommerceApp.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ProductService extends AbstractBaseService<Product, Long> {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductImagesRepository productImagesRepository;
    @Autowired
    private ProductImageFactory productImageFactory;


    private ProductRepository repository;
    public ProductService(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Product> findAllByQuery(String name) {
        return repository.findAllByQuery(name);
    }

    public List<Product> findByProductCategoryId(Long productCategoryId) {
        return repository.findByProductCategoryId(productCategoryId);
    }


    public Product updateProductById(Long productId, ProductRequestDTO requestDTO) throws ResourceNotFoundException {
        Product product = findById(productId);
        Long productCategoryId = requestDTO.getProductCategoryId();
        ProductCategory productCategory = productCategoryService.findById(productCategoryId);

//        product.setId(requestDTO.getId()); //TODO  ?????
        product.setTitle(requestDTO.getTitle());
        product.setQuantity(requestDTO.getQuantity());
        product.setDescription(requestDTO.getDescription());
        product.setPrice(requestDTO.getPrice());
        product.setThumbnail(requestDTO.getThumbnail());
        product.setColor(requestDTO.getColor());
        product.setProductCategory(productCategory);

        List<String> productImages = requestDTO.getProductImages();
        productImageFactory.addImagesToProductFromImagesList(product, productImages);
        return repository.save(product);
    }


}
