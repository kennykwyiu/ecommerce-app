package finalproject.EcommerceApp.controller.UserProduct;

import finalproject.EcommerceApp.dto_request.ShoppingCartRequestDTO;
import finalproject.EcommerceApp.dto_response.ShoppingOrderResponseDTO;
import finalproject.EcommerceApp.exception.InsufficientInventoryException;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.factory.ShoppingOrderFactory;
import finalproject.EcommerceApp.model.ShoppingOrder;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.service.CheckOutService;
import finalproject.EcommerceApp.service.OrderProcessingService;
import finalproject.EcommerceApp.service.ShoppingCartService;
import finalproject.EcommerceApp.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping
    public List<ShoppingOrderResponseDTO> getHistoricalOrders(Principal principal) throws ResourceNotFoundException {
        String externalUserId = principal.getName();
        SystemUser systemUser = getSystemUser(externalUserId);

        return orderProcessingService.findAllOrderBySystemUser(systemUser).stream()
        .map(shoppingOrderFactory::createDTOFromShoppingOrder)
        .toList();
    }


    @PostMapping
    public ResponseEntity<ShoppingOrderResponseDTO> checkOut(Principal principal)
            throws ResourceNotFoundException, InsufficientInventoryException {

        String externalUserId = principal.getName();
        SystemUser systemUser = getSystemUser(externalUserId);

        ShoppingCartRequestDTO shoppingCartRequestDTO = shoppingCartService.getCart(systemUser.getId());

        if (shoppingCartRequestDTO.getCartItemRequestDTOS().isEmpty()) {
            throw new IllegalStateException("Your shopping cart is empty");
        }

        ShoppingOrder shoppingOrder = checkOutService.checkOut(shoppingCartRequestDTO, systemUser);
        ShoppingOrderResponseDTO shoppingOrderResponseDTO =
                shoppingOrderFactory.createDTOFromShoppingOrder(shoppingOrder);
        shoppingCartService.clearCart(systemUser.getId());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(shoppingOrderResponseDTO);
    }

    private SystemUser getSystemUser(String externalUserId) throws ResourceNotFoundException {
        return systemUserService.findByExternalUserId(externalUserId);
    }
}
