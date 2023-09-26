package finalproject.EcommerceApp.controller.UserProduct;

import finalproject.EcommerceApp.dto_request.ShoppingCartRequestDTO;
import finalproject.EcommerceApp.dto_response.ShoppingOrderResponseDTO;
import finalproject.EcommerceApp.exception.InsufficientInventoryException;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.factory.ShoppingOrderFactory;
import finalproject.EcommerceApp.factory.SystemUserAddressFactory;
import finalproject.EcommerceApp.dto_response.SystemUserAddressResponseDTO;
import finalproject.EcommerceApp.model.ShoppingOrder;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.model.SystemUserAddress;
import finalproject.EcommerceApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users/orders")
public class UserOrderController {

    @Autowired
    private OrderProcessingService orderProcessingService;

    @Autowired
    private ShoppingOrderFactory shoppingOrderFactory;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private CheckOutService checkOutService;
    @Autowired
    private SystemUserAddressService systemUserAddressService;

    @Autowired
    private SystemUserAddressFactory systemUserAddressFactory;

    @Autowired
    private ProductService productService;

    @GetMapping // OK
    public List<ShoppingOrderResponseDTO> getHistoricalOrders(SystemUser systemUser) throws ResourceNotFoundException {

        List<ShoppingOrderResponseDTO> shoppingOrderResponseDTOS = new ArrayList<>();
        List<ShoppingOrder> shoppingOrders = orderProcessingService.findAllOrderBySystemUser(systemUser);
        for (ShoppingOrder shoppingOrder : shoppingOrders) {
            ShoppingOrderResponseDTO shoppingOrderResponseDTO = shoppingOrderFactory.createDTOFromShoppingOrder(shoppingOrder);
            shoppingOrderResponseDTOS.add(shoppingOrderResponseDTO);
        }
        return shoppingOrderResponseDTOS;
    }


    @PostMapping // OK
    public ResponseEntity<ShoppingOrderResponseDTO> checkOut(SystemUser systemUser)
            throws ResourceNotFoundException, InsufficientInventoryException {

        ShoppingCartRequestDTO shoppingCartRequestDTO = shoppingCartService.getCart(systemUser.getId());

        if (shoppingCartRequestDTO.getCartItemRequestDTOS().isEmpty()) {
            throw new IllegalStateException("Your shopping cart is empty");
        }

        SystemUserAddress systemUserAddress = systemUserAddressService.getActiveSystemUserAddress(systemUser);

        SystemUserAddressResponseDTO systemUserAddressResponseDTO
                = systemUserAddressFactory.toResponseDTO(systemUserAddress);

        ShoppingOrder shoppingOrder = checkOutService.checkOut(shoppingCartRequestDTO, systemUserAddress);

        ShoppingOrderResponseDTO shoppingOrderResponseDTO =
                shoppingOrderFactory.createDTOFromShoppingOrder(shoppingOrder, systemUserAddressResponseDTO);

        shoppingOrderResponseDTO.getShoppingOrderItemResponseDTOS().stream()
                .map(shoppingOrderItemResponseDTO -> shoppingOrderItemResponseDTO.getProductResponseDTO().getId())
                .forEach(id -> {
                    try {
                        productService.findById(id).setQuantity(0);
                    } catch (ResourceNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });

        shoppingCartService.clearCart(systemUser.getId());


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(shoppingOrderResponseDTO);
    }
}
