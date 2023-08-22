package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.dto_request.ShoppingCartRequestDTO;
import finalproject.EcommerceApp.exception.InsufficientInventoryException;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.model.ShoppingOrderWrapper;
import finalproject.EcommerceApp.model.ShoppingOrder;
import finalproject.EcommerceApp.model.SystemUser;
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
                                  SystemUser systemUser) throws ResourceNotFoundException, InsufficientInventoryException {
        ShoppingOrderWrapper shoppingOrderWrapper = orderProcessingService.createOrder(requestDTO, systemUser);

        inventoryService.checkStock(shoppingOrderWrapper);


//        orderProcessingService.takeProductSnapShot(checkOutBasket);
//        paymentService.settlePayment(checkOutBasket);
//        deliveryService.deliver(checkOutBasket);

        return shoppingOrderWrapper.getShoppingOrder();
    }

}
