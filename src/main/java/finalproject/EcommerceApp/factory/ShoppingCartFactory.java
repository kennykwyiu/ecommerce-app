package finalproject.EcommerceApp.factory;

import finalproject.EcommerceApp.dto_request.ShoppingCartItemRequestDTO;
import finalproject.EcommerceApp.dto_request.ShoppingCartRequestDTO;
import finalproject.EcommerceApp.dto_response.ProductResponseDTO;
import finalproject.EcommerceApp.dto_response.ShoppingCartItemResponseDTO;
import finalproject.EcommerceApp.dto_response.ShoppingCartResponseDTO;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.model.Product;
import finalproject.EcommerceApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingCartFactory {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductFactory productFactory;

    public ShoppingCartResponseDTO toResponseDTO(ShoppingCartRequestDTO requestDTO) throws ResourceNotFoundException {
        List<ShoppingCartItemRequestDTO> cartItemRequestDTOS = requestDTO.getCartItemRequestDTOS();
        List<ShoppingCartItemResponseDTO> cartItemResponseDTOS = createCartItemResponseDTOS(cartItemRequestDTOS);

        return ShoppingCartResponseDTO.builder()
                .cartItemResponseDTOS(cartItemResponseDTOS)
                .build();
    }

    private List<ShoppingCartItemResponseDTO> createCartItemResponseDTOS(List<ShoppingCartItemRequestDTO> cartItemRequestDTOS) throws ResourceNotFoundException {
        List<ShoppingCartItemResponseDTO> cartItemResponseDTOS = new ArrayList<>();

        for (ShoppingCartItemRequestDTO item : cartItemRequestDTOS) {

            Product product;
            try {
                product = productService.findById(item.getProductId());
            } catch (ResourceNotFoundException e) {
                product = Product.builder().title("No Longer Exists").build();
            }

            ProductResponseDTO productResponseDTO = productFactory.toDto(product);

            ShoppingCartItemResponseDTO cartItemResponseDTO = ShoppingCartItemResponseDTO.builder()
                    .product(productResponseDTO)
                    .quantity(item.getQuantity())
                    .build();

            cartItemResponseDTOS.add(cartItemResponseDTO);
        }
        return cartItemResponseDTOS;
    }

}
