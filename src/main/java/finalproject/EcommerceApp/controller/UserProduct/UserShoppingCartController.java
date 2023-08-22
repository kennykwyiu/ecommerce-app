package finalproject.EcommerceApp.controller.UserProduct;

import finalproject.EcommerceApp.dto_request.ShoppingCartRequestDTO;
import finalproject.EcommerceApp.dto_response.EmptyResponseDTO;
import finalproject.EcommerceApp.dto_response.ShoppingCartResponseDTO;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.factory.ShoppingCartFactory;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.service.ShoppingCartService;
import finalproject.EcommerceApp.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users/cart")
public class UserShoppingCartController {

    @Autowired
    private ShoppingCartFactory shoppingCartFactory;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private SystemUserService systemUserService;

    @PostMapping
    public ResponseEntity<EmptyResponseDTO> updateCart(@RequestBody ShoppingCartRequestDTO requestDTO,
                                                       Principal principal) throws ResourceNotFoundException {
        String externalUserId = principal.getName();
        SystemUser systemUser = systemUserService.findByExternalUserId(externalUserId);
        Long systemUserId = systemUser.getId();
        shoppingCartService.replaceItemToCart(systemUserId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EmptyResponseDTO());
    }

    @GetMapping
    public ShoppingCartResponseDTO retrieveCart(Principal principal) throws ResourceNotFoundException {
        String externalUserId = principal.getName();
        SystemUser systemUser = systemUserService.findByExternalUserId(externalUserId);
        ShoppingCartRequestDTO requestDTO = shoppingCartService.getCart(systemUser.getId());
        return shoppingCartFactory.toResponseDTO(requestDTO);
    }

}
