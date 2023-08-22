package finalproject.EcommerceApp.dto_request;

import finalproject.EcommerceApp.model.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Builder
public class ProductImagesRequestDTO {
    private List<String> urls;
    private Long productId;

}
