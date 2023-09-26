package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.dto_request.ShoppingCartRequestDTO;
import finalproject.EcommerceApp.exception.InsufficientInventoryException;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.dto_request.ShoppingOrderWrapper;
import finalproject.EcommerceApp.model.ShoppingOrder;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.model.SystemUserAddress;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CheckOutService {

    @Autowired
    private OrderProcessingService orderProcessingService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DeliveryService deliveryService;

    public ShoppingOrder checkOut(ShoppingCartRequestDTO requestDTO,
                                  SystemUserAddress systemUserAddress) throws ResourceNotFoundException, InsufficientInventoryException {
        SystemUser systemUser = systemUserAddress.getSystemUser();
        String activeAddress = systemUserAddress.toString();
        ShoppingOrderWrapper shoppingOrderWrapper = orderProcessingService.createOrder(requestDTO,
                systemUser, activeAddress);

//        inventoryService.checkStock(shoppingOrderWrapper);
        paymentService.settlePayment(shoppingOrderWrapper);
        deliveryService.deliver(shoppingOrderWrapper, systemUserAddress);

        return shoppingOrderWrapper.getShoppingOrder();
    }

}
