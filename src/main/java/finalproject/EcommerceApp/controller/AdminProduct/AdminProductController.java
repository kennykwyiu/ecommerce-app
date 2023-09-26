package finalproject.EcommerceApp.controller.AdminProduct;

import finalproject.EcommerceApp.dto_request.ProductImagesRequestDTO;
import finalproject.EcommerceApp.dto_request.ProductRequestDTO;
import finalproject.EcommerceApp.dto_response.EmptyResponseDTO;
import finalproject.EcommerceApp.dto_response.ProductResponseDTO;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.factory.ProductFactory;
import finalproject.EcommerceApp.factory.ProductImageFactory;
import finalproject.EcommerceApp.model.Product;
import finalproject.EcommerceApp.model.ProductImages;
import finalproject.EcommerceApp.service.ProductImagesService;
import finalproject.EcommerceApp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductFactory productFactory;

    @Autowired
    private ProductImageFactory productImageFactory;

    @Autowired
    private ProductImagesService productImagesService;


    @GetMapping // ok
    public List<ProductResponseDTO> getAll(@RequestParam(name = "qc", required = false) Long productCategoryId,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        if (productCategoryId == null) {
            return productService.findAll(PageRequest.of(page, size)).stream()
                    .map(productFactory::toDto)
                    .toList();
        }
        return productService.findByProductCategoryId(productCategoryId).stream()
                .map(productFactory::toDto)
                .toList();
    }


    @GetMapping("/{productId}") // OK
    public ProductResponseDTO findProductByProductId(@PathVariable(name = "productId") Long productId) throws ResourceNotFoundException {
        return productFactory.toDto(productService.findById(productId));
    }

    @PostMapping // ok
    public ResponseEntity<EmptyResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO requestDTO) throws ResourceNotFoundException {
        Product createdProduct = productFactory.toEntity(requestDTO);
        productService.save(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EmptyResponseDTO());
    }

    @PutMapping("/{productId}") // OK
    public ResponseEntity<ProductResponseDTO> updateProductById(@PathVariable(name = "productId") Long productId,
                                                                @Valid @RequestBody ProductRequestDTO requestDTO) throws ResourceNotFoundException {
        Product updatedProduct = productService.updateProductById(productId, requestDTO);
        ProductResponseDTO updatedResponseDTO = productFactory.toDto(updatedProduct);
        productService.save(updatedProduct);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedResponseDTO);
    }

    @DeleteMapping("/{productId}") //OK
    public ResponseEntity<EmptyResponseDTO> removeProduct(@PathVariable(name = "productId") Long productId) throws ResourceNotFoundException {
        productService.delete(productService.findById(productId));
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/{productId}/product-images") // OK
    public ResponseEntity<EmptyResponseDTO> updateProductImagesById(@PathVariable(name = "productId") Long productId,
                                                                    @Valid @RequestBody ProductImagesRequestDTO requestDTO) throws ResourceNotFoundException {
        Product product = productService.findById(productId);
        List<String> imagesList = requestDTO.getUrls();
        productImageFactory.addImagesToProductFromImagesList(product, imagesList);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EmptyResponseDTO());
    }

    //@PathVariable(name = "productId") Long productId,
    @DeleteMapping("/product-images/{productImagesId}")
    public ResponseEntity<EmptyResponseDTO> removeImagesById(@PathVariable(name = "productImagesId") Long productImagesId) throws ResourceNotFoundException {
        ProductImages productImages = productImagesService.findById(productImagesId);
        productImagesService.delete(productImages);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
