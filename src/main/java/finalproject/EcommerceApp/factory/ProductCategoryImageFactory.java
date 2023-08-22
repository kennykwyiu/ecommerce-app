package finalproject.EcommerceApp.factory;

import finalproject.EcommerceApp.model.ProductCategory;
import finalproject.EcommerceApp.model.ProductCategoryImages;
import finalproject.EcommerceApp.repository.ProductCategoryImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductCategoryImageFactory {

    @Autowired
    private ProductCategoryImagesRepository productCategoryImagesRepository;
    public void addImagesToProductFromImagesList(ProductCategory productCategory, List<String> imagesList) {
        imagesList.forEach(url -> productCategoryImagesRepository.save(
                ProductCategoryImages.builder()
                        .url(url)
                        .productCategory(productCategory)
                        .build()
        ));
    }
}
