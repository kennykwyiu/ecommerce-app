package finalproject.EcommerceApp.controller.UserProduct;

import finalproject.EcommerceApp.dto_response.ProductResponseDTO;
import finalproject.EcommerceApp.factory.ProductFactory;
import finalproject.EcommerceApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.PageRanges;
import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/products")
public class UserProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductFactory productFactory;

    //@RequestParam(defaultValue = "0") int page,
    //@RequestParam(defaultValue = "10") int size

    @GetMapping // OK
    public List<ProductResponseDTO> getAll(@RequestParam(name = "qc", required = false) Long productCategoryId) {

        if (productCategoryId == null) {
            return productService.findAll().stream()
                    .map(productFactory::toDto)
                    .toList();
        }
        return productService.findByProductCategoryId(productCategoryId).stream()
                .map(productFactory::toDto)
                .toList();
    }

}
