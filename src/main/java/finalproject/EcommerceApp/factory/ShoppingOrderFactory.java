package finalproject.EcommerceApp.factory;

import finalproject.EcommerceApp.dto_response.*;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.model.*;
import finalproject.EcommerceApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShoppingOrderFactory {
    @Autowired
    private ShoppingOrderItemService shoppingOrderItemService;
    @Autowired
    private ProductCategoryFactory productCategoryFactory;
    @Autowired
    private ProductImageFactory productImageFactory;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImagesService productImagesService;
    @Autowired
    private SystemUserAddressFactory systemUserAddressFactory;
    @Autowired
    private  SystemUserAddressService systemUserAddressService;
    @Autowired
    private SystemUserService systemUserService;

    public ShoppingOrderResponseDTO createDTOFromShoppingOrder(ShoppingOrder shoppingOrder,
                                                               SystemUserAddressResponseDTO systemUserAddressResponseDTO) {
        List<ShoppingOrderItem> shoppingOrderItems
                = shoppingOrderItemService.findAllByShoppingOrder(shoppingOrder);

        /*
                List<ShoppingOrderItemResponseDTO> shoppingOrderItemResponseDTOS = new ArrayList<>();
        for (ShoppingOrderItem shoppingOrderItem : shoppingOrderItems) {
            ShoppingOrderItemResponseDTO build = ShoppingOrderItemResponseDTO.builder()
                    .productResponseDTO(createProductResponseDTOFromProductSnapshot(shoppingOrderItem
                    .getProductSnapshot()))
                    .quantity(shoppingOrderItem.getQuantity())
                    .build();
            shoppingOrderItemResponseDTOS.add(build);
        }
         */
        List<ShoppingOrderItemResponseDTO> shoppingOrderItemResponseDTOS
                        = shoppingOrderItems.stream()
                                .map(shoppingOrderItem -> ShoppingOrderItemResponseDTO.builder()
                                .productResponseDTO(createProductResponseDTOFromProductSnapshot(shoppingOrderItem.getProductSnapshot()))
                                .quantity(shoppingOrderItem.getQuantity())
                                .build())
                                .collect(Collectors.toList());


        return ShoppingOrderResponseDTO.builder()
                .shoppingOrderItemResponseDTOS(shoppingOrderItemResponseDTOS)
                .total(shoppingOrder.getTotal())
                .createdAt(shoppingOrder.getCreatedAt())
                .orderStatus(shoppingOrder.getStatus())
//                .systemUserAddressResponseDTO(systemUserAddressResponseDTO)
                .activeAddress(shoppingOrder.getActiveAddress())
                .build();

    }

    public ShoppingOrderResponseDTO createDTOFromShoppingOrder(ShoppingOrder shoppingOrder) throws ResourceNotFoundException {
        List<ShoppingOrderItem> shoppingOrderItems
                = shoppingOrderItemService.findAllByShoppingOrder(shoppingOrder);
//        SystemUser systemUser = shoppingOrder.getSystemUser();
//        SystemUserAddress systemUserAddress = systemUserAddressService.getActiveSystemUserAddress(systemUser);
//        SystemUserAddressResponseDTO systemUserAddressResponseDTO
//                = systemUserAddressFactory.toResponseDTO(systemUserAddress);

        List<ShoppingOrderItemResponseDTO> shoppingOrderItemResponseDTOS
                = shoppingOrderItems.stream()
                .map(shoppingOrderItem -> ShoppingOrderItemResponseDTO.builder()
                        .productResponseDTO(createProductResponseDTOFromProductSnapshot(shoppingOrderItem.getProductSnapshot()))
                        .quantity(shoppingOrderItem.getQuantity())
                        .build())
                .collect(Collectors.toList());


        return ShoppingOrderResponseDTO.builder()
                .shoppingOrderItemResponseDTOS(shoppingOrderItemResponseDTOS)
                .total(shoppingOrder.getTotal())
                .createdAt(shoppingOrder.getCreatedAt())
                .orderStatus(shoppingOrder.getStatus())
//                .systemUserAddressResponseDTO(systemUserAddressResponseDTO)
                .activeAddress(shoppingOrder.getActiveAddress())
                .build();

    }

    private ProductResponseDTO createProductResponseDTOFromProductSnapshot(ProductSnapshot productSnapshot) {
        ProductCategory productCategory = productSnapshot.getProductCategory();
        ProductCategoryResponseDTO productCategoryResponseDTO = productCategoryFactory.toDto(productCategory);

        Product product = productSnapshot.getProduct();
        List<String> productImagesList = productImagesService.getAllByProduct(product);

        return ProductResponseDTO.builder()
                .id(product.getId())
                .title(productSnapshot.getTitle())
                .quantity(productSnapshot.getQuantity())
                .description(productSnapshot.getDescription())
                .price(productSnapshot.getPrice())
                .thumbnail(productSnapshot.getThumbnail())
                .productCategoryResponseDTO(productCategoryResponseDTO)
                .countryOfOrigin(productSnapshot.getCountryOfOrigin())
                .color(productSnapshot.getColor())
                .productImages(productImagesList)
                .build();
    }

}
