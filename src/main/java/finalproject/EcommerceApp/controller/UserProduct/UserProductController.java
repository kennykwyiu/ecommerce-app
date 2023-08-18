package finalproject.EcommerceApp.controller.UserProduct;

import finalproject.EcommerceApp.dto_response.ProductResponseDTO;
import finalproject.EcommerceApp.factory.ProductFactory;
import finalproject.EcommerceApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/products")
public class UserProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductFactory productFactory;

    @GetMapping
    public List<ProductResponseDTO> findAll() {
        return productService.findAll().stream()
                .map(productFactory::toDto)
                .collect(Collectors.toList());
    }



}
