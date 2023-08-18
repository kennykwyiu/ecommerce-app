package finalproject.EcommerceApp.dto_request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ProductCategoryRequestDTO {

    private String categoryName;

    private String categoryDescription;

}
