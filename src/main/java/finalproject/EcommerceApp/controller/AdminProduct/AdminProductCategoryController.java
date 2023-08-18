package finalproject.EcommerceApp.controller.AdminProduct;

import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.model.Product;
import finalproject.EcommerceApp.service.ProductCategoryService;
import finalproject.EcommerceApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product-category")
public class AdminProductCategoryController {

    @Autowired
     private ProductService productService;


    @GetMapping("/{productCategoryId}") // OK
    public List<Product> findProductByProductId(@PathVariable Long productCategoryId) {
        return productService.findByProductCategoryId(productCategoryId);
    }

}
