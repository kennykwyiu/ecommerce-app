package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.dto_request.ShoppingCartItemRequestDTO;
import finalproject.EcommerceApp.dto_request.ShoppingCartRequestDTO;
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

    public ShoppingOrderWrapper createOrder(ShoppingCartRequestDTO requestDTO,
                                            SystemUser systemUser) throws ResourceNotFoundException {

        // TODO: total and SystemUser to create ShoppingOrder
        //  add systemUser and status first and setTotal later
        ShoppingOrder shoppingOrder = ShoppingOrder.builder()
                .systemUser(systemUser)
                .status(ShoppingOrderStatus.PENDING_PAYMENT)
                .build();

        shoppingOrderRepository.save(shoppingOrder);

        // TODO: List<ShoppingOrderItem>
        //  ShoppingOrderItem: ShoppingOrder, ProductSnapshot, quantity
        //  and add ShoppingOrderItem into List<ShoppingOrderItem>

        List<ShoppingOrderItem> shoppingOrderItems = new ArrayList<>();
        List<ShoppingCartItemRequestDTO> cartItemRequestDTOS = requestDTO.getCartItemRequestDTOS();
        BigDecimal orderTotal = BigDecimal.ZERO;
        for (ShoppingCartItemRequestDTO item : cartItemRequestDTOS) {
            Product product = productService.findById(item.getProductId());

            ShoppingOrderItem shoppingOrderItem = createdOrderItem(shoppingOrder,
                    item,
                    product);
            shoppingOrderItems.add(shoppingOrderItem);

            orderTotal = orderTotal.add(product.getPrice().multiply(new BigDecimal(item.getQuantity())));

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

    private ShoppingOrderItem createdOrderItem(ShoppingOrder shoppingOrder,
                                  ShoppingCartItemRequestDTO item,
                                  Product product) throws ResourceNotFoundException {
        List<ProductSnapshot> productSnapshots
                = productSnapShotService.findLatestSnapShotByProductId(product);

        if (isProductSnapShotLatest(productSnapshots, product)) {

            return ShoppingOrderItem.builder()
                    .productSnapshot(productSnapshots.get(0))
                    .quantity(item.getQuantity())
                    .shoppingOrder(shoppingOrder)
                    .build();
        } else {
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

        return ShoppingOrderItem.builder()
                .productSnapshot(newProductSnapshot)
                .quantity(item.getQuantity())
                .shoppingOrder(shoppingOrder)
                .build();
        }
    }

    private boolean isProductSnapShotLatest(List<ProductSnapshot> productSnapshots,
                                            Product product) {
        if (productSnapshots.isEmpty()) {
            return false;
        }
        ProductSnapshot productSnapshot = productSnapshots.get(0);
        return productSnapshot.getCreatedAt().isAfter(product.getUpdatedAt());
    }

    public void takeProductSnapShot(ShoppingOrderWrapper shoppingOrder) {

    }

    public List<ShoppingOrder> findAllOrderBySystemUser(SystemUser systemUser) {
        return shoppingOrderRepository.findAllBySystemUser(systemUser);
    }
}
