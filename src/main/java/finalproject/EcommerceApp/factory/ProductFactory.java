package finalproject.EcommerceApp.factory;

import finalproject.EcommerceApp.dto_request.ProductRequestDTO;
import finalproject.EcommerceApp.dto_response.ProductResponseDTO;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.model.Product;
import finalproject.EcommerceApp.model.ProductCategory;
import finalproject.EcommerceApp.service.ProductCategoryService;
import finalproject.EcommerceApp.service.ProductImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductFactory {

    @Autowired
    private ProductImagesService productImagesService;

    @Autowired
    private ProductCategoryFactory productCategoryFactory;

    @Autowired
    private ProductCategoryService productCategoryService;

    public ProductResponseDTO toDto(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .color(product.getColor())
                .productCategoryResponseDTO(productCategoryFactory.toDto(product.getProductCategory()))
                .countryOfOrigin(product.getCountryOfOrigin())
                .quantity(product.getQuantity())
                .thumbnail(product.getThumbnail())
                .productImages(productImagesService.getAllByProduct(product))
                .build();
    }


    public Product toEntity(ProductRequestDTO requestDTO) throws ResourceNotFoundException {
        ProductCategory productCategory = productCategoryService.findById(requestDTO.getProductCategoryId());

        return Product.builder()
                .title(requestDTO.getTitle())
                .description(requestDTO.getDescription())
                .price(requestDTO.getPrice())
                .color(requestDTO.getColor())
                .productCategory(productCategory)
                .countryOfOrigin(requestDTO.getCountryOfOrigin())
                .quantity(requestDTO.getQuantity())
                .thumbnail(requestDTO.getThumbnail())
//                .productImages(productImagesService.getAllByProduct(requestDTO.)
                        .build();
    }

}
