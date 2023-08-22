package finalproject.EcommerceApp.dto_request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductCategoryImagesRequestDTO {

    private List<String> urls;
    private Long productCategoryId;

}
