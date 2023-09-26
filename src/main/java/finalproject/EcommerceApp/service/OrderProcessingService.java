package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.dto_request.ShoppingCartItemRequestDTO;
import finalproject.EcommerceApp.dto_request.ShoppingCartRequestDTO;
import finalproject.EcommerceApp.dto_request.ShoppingOrderWrapper;
import finalproject.EcommerceApp.exception.InsufficientInventoryException;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.model.*;
import finalproject.EcommerceApp.repository.ShoppingOrderItemRepository;
import finalproject.EcommerceApp.repository.ShoppingOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static finalproject.EcommerceApp.model.ShoppingOrderStatus.INSUFFICIENT_INVENTORY;

@Service
@Transactional
public class OrderProcessingService {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSnapShotService productSnapShotService;
    @Autowired
    private ShoppingOrderRepository shoppingOrderRepository;
    @Autowired
    private ShoppingOrderItemRepository shoppingOrderItemRepository;
    @Autowired
    private InventoryService inventoryService;

    public List<ShoppingOrder> findAllOrderBySystemUser(SystemUser systemUser) {
        return shoppingOrderRepository.findAllBySystemUser(systemUser);
    }

    public ShoppingOrderWrapper createOrder(ShoppingCartRequestDTO requestDTO,
                                            SystemUser systemUser,
                                            String activeAddress) throws ResourceNotFoundException,
                                                                    InsufficientInventoryException {

        // TODO: total and SystemUser to create ShoppingOrder
        //  add systemUser and status first and setTotal later
        ShoppingOrder shoppingOrder = ShoppingOrder.builder()
                .systemUser(systemUser)
                .status(ShoppingOrderStatus.PENDING_PAYMENT)
                .activeAddress(activeAddress)
                .build();

//        shoppingOrderRepository.save(shoppingOrder);

        // TODO: List<ShoppingOrderItem>
        //  ShoppingOrderItem: ShoppingOrder, ProductSnapshot, quantity
        //  and add ShoppingOrderItem into List<ShoppingOrderItem>

        List<ShoppingOrderItem> shoppingOrderItems = new ArrayList<>();
        List<ShoppingCartItemRequestDTO> cartItemRequestDTOS = requestDTO.getCartItemRequestDTOS();
        BigDecimal orderTotal = BigDecimal.ZERO;
        StringBuilder errorMessage = new StringBuilder();
        for (ShoppingCartItemRequestDTO item : cartItemRequestDTOS) {
            Product product = productService.findById(item.getProductId());

            ShoppingOrderItem shoppingOrderItem;
            List<ProductSnapshot> productSnapshots
                    = productSnapShotService.findLatestSnapShotByProductId(product);

            ProductSnapshot productSnapshot;
            if (isProductSnapShotLatest(productSnapshots, product)) {
                productSnapshot = productSnapshots.get(0);
            } else {
                productSnapshot= takeProductSnapShot(product);
            }
            shoppingOrderItem = getShoppingOrderItem(productSnapshot,
                    item,
                    shoppingOrder);

            /*
                        if (isProductSnapShotLatest(productSnapshots, product)) {
                ProductSnapshot productSnapshot = productSnapshots.get(0);
                shoppingOrderItem = getShoppingOrderItem(productSnapshot, item, shoppingOrder);
            } else {
                ProductSnapshot newProductSnapshot = takeSanpShot(product);
                shoppingOrderItem = getShoppingOrderItem(newProductSnapshot, item, shoppingOrder);
            }
             */

            inventoryService.checkStock(errorMessage, shoppingOrderItem);
            shoppingOrderItems.add(shoppingOrderItem);
            orderTotal = computeOrderTotal(orderTotal, item, product);
        }

        if (!errorMessage.isEmpty()) {
            shoppingOrder.setStatus(INSUFFICIENT_INVENTORY);
            throw new InsufficientInventoryException(errorMessage.toString());
        }

        shoppingOrder.setTotal(orderTotal);
        shoppingOrderRepository.save(shoppingOrder);
        shoppingOrderItemRepository.saveAll(shoppingOrderItems);

        // TODO: need to create ShoppingOrder AND List<ShoppingOrderItem>,
        //  and need both of it to create CheckOutBasket
        return ShoppingOrderWrapper.builder()
                .shoppingOrder(shoppingOrder)
                .shoppingOrderItems(shoppingOrderItems)
                .build();
    }

    private static ShoppingOrderItem getShoppingOrderItem(ProductSnapshot productSnapshot,
                                                          ShoppingCartItemRequestDTO item,
                                                          ShoppingOrder shoppingOrder) {
        ShoppingOrderItem shoppingOrderItem;
        shoppingOrderItem = ShoppingOrderItem.builder()
                .productSnapshot(productSnapshot)
                .quantity(item.getQuantity())
                .shoppingOrder(shoppingOrder)
                .build();
        return shoppingOrderItem;
    }

    private ProductSnapshot takeProductSnapShot(Product product) {
        ProductSnapshot newProductSnapshot = ProductSnapshot.builder()
                .title(product.getTitle())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .price(product.getPrice())
                .thumbnail(product.getThumbnail())
                .countryOfOrigin(product.getCountryOfOrigin())
                .color(product.getColor())
                .productCategory(product.getProductCategory())
                .product(product)
                .build();
        productSnapShotService.save(newProductSnapshot);
        return newProductSnapshot;
    }

    private static BigDecimal computeOrderTotal(BigDecimal orderTotal,
                                                ShoppingCartItemRequestDTO item,
                                                Product product) {
        return orderTotal.add(product.getPrice().multiply(new BigDecimal(item.getQuantity())));
    }


    private boolean isProductSnapShotLatest(List<ProductSnapshot> productSnapshots,
                                            Product product) {
        if (productSnapshots.isEmpty()) {
            return false;
        }
        ProductSnapshot productSnapshot = productSnapshots.get(0);
        return productSnapshot.getCreatedAt().isAfter(product.getUpdatedAt());
    }
}
