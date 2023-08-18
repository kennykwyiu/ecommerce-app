package finalproject.EcommerceApp.dto_request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ProductRequestDTO {
    private Long id;

    private String title;

    private Integer quantity;

    private String description;

    private BigDecimal price;

    private String thumbnail;

    private Long productCategoryId;

    private String countryOfOrigin;

    private String color;

    private List<String> productImages;

}
