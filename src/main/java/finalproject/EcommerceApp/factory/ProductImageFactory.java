package finalproject.EcommerceApp.factory;

import finalproject.EcommerceApp.model.Product;
import finalproject.EcommerceApp.model.ProductImages;
import finalproject.EcommerceApp.repository.ProductImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductImageFactory {
    @Autowired
    private ProductImagesRepository productImagesRepository;

    public void addImagesToProductFromImagesList(Product product, List<String> productImages) {
        for (String image : productImages) {
            ProductImages productImages1 = ProductImages.builder()
                    .url(image)
                    .product(product)
                    .build();
            productImagesRepository.save(productImages1);
        }
    }



}
