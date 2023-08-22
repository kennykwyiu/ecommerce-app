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

    public void addImagesToProductFromImagesList(Product product, List<String> urlList) {
        urlList.forEach(url -> productImagesRepository.save(
                ProductImages.builder()
                        .url(url)
                        .product(product)
                        .build()
        ));


//        for (String url : urlList) {
//            ProductImages productImages = ProductImages.builder()
//                    .url(url)
//                    .product(product)
//                    .build();
//            productImagesRepository.save(productImages);
//    }
    }
}



