package finalproject.EcommerceApp.dto_response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ProductCategoryResponseDTO {
    private Long id;

    private String categoryName;

    private String categoryDescription;

}
