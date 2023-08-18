package finalproject.EcommerceApp.factory;

import finalproject.EcommerceApp.dto_request.ProductCategoryRequestDTO;
import finalproject.EcommerceApp.dto_response.ProductCategoryResponseDTO;
import finalproject.EcommerceApp.model.ProductCategory;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryFactory {
    public ProductCategoryResponseDTO toDto(ProductCategory productCategory) {
        return ProductCategoryResponseDTO.builder()
                .id(productCategory.getId())
                .categoryName(productCategory.getCategoryName())
                .categoryDescription(productCategory.getCategoryDescription())
                .build();
    }

    public ProductCategory toEntity(ProductCategoryRequestDTO productCategoryRequestDTO) {
        return ProductCategory.builder()
                .categoryName(productCategoryRequestDTO.getCategoryName())
                .categoryDescription(productCategoryRequestDTO.getCategoryDescription())
                .build();
    }
}
