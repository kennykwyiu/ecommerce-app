package finalproject.EcommerceApp.controller.AdminProduct;

import finalproject.EcommerceApp.dto_request.ProductCategoryImagesRequestDTO;
import finalproject.EcommerceApp.dto_response.EmptyResponseDTO;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.factory.ProductCategoryImageFactory;
import finalproject.EcommerceApp.model.Product;
import finalproject.EcommerceApp.model.ProductCategory;
import finalproject.EcommerceApp.model.ProductCategoryImages;
import finalproject.EcommerceApp.service.ProductCategoryImagesService;
import finalproject.EcommerceApp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/product-category")
public class AdminProductCategoryController {

    @Autowired
     private ProductService productService;
    @Autowired
    private ProductCategoryImagesService productCategoryImagesService;

    @Autowired
    private ProductCategoryImageFactory productCategoryImageFactory;


    @GetMapping("/{productCategoryId}") // OK
    public List<Product> findProductByProductCategoryId(@PathVariable Long productCategoryId) {
        return productService.findByProductCategoryId(productCategoryId);
    }

    @PostMapping("/{productCategoryId}/product-category-images") // OK
    public ResponseEntity<EmptyResponseDTO> updateProductCategoryImagesById(@PathVariable(name = "productCategoryId") Long productCategoryId,
                                                                    @Valid @RequestBody ProductCategoryImagesRequestDTO requestDTO) throws ResourceNotFoundException {
        ProductCategory productCategory = productCategoryImagesService.findById(productCategoryId).getProductCategory();
        List<String> imagesList = requestDTO.getUrls();
        productCategoryImageFactory.addImagesToProductFromImagesList(productCategory, imagesList);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EmptyResponseDTO());
    }

    @DeleteMapping("/product-category-images/{productCategoryImagesId}")
    public ResponseEntity<EmptyResponseDTO> removeImagesById(@PathVariable(name = "productCategoryImagesId") Long productCategoryImagesId) throws ResourceNotFoundException {
        ProductCategoryImages productCategoryImages = productCategoryImagesService.findById(productCategoryImagesId);
        productCategoryImagesService.delete(productCategoryImages);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
