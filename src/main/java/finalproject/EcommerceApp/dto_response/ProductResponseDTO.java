package finalproject.EcommerceApp.dto_response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;

    private String title;

    private Integer quantity;

    private String description;

    private BigDecimal price;

    private String thumbnail;

    private ProductCategoryResponseDTO productCategoryResponseDTO;

    private String countryOfOrigin;

    private String color;

    private List<String> productImages;

}
