package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.dto_request.ShoppingCartItemRequestDTO;
import finalproject.EcommerceApp.dto_request.ShoppingCartRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Transactional
@Service
public class ShoppingCartService {
    Map<Long, ShoppingCartRequestDTO> shoppingCartRequestDTOMap = new ConcurrentHashMap<>();

    public void replaceItemToCart(Long systemUserId,
                              ShoppingCartRequestDTO requestDTO) {
        shoppingCartRequestDTOMap.put(systemUserId, requestDTO);
    }

    public void clearCart(Long systemUserId) {
        shoppingCartRequestDTOMap.remove(systemUserId);
    }

    public ShoppingCartRequestDTO getCart(Long systemUserId) {
//        if (!shoppingCartRequestDTOMap.containsKey(systemUserId)) {
//        shoppingCartRequestDTOMap.put(systemUserId, new ShoppingCartRequestDTO());
//        }
//        return shoppingCartRequestDTOMap.get(systemUserId);

        return shoppingCartRequestDTOMap.computeIfAbsent(systemUserId,
                (Long k) -> new ShoppingCartRequestDTO());
            /*
            In this version, the computeIfAbsent method is used to get
            the value associated with the systemUserId key from the
            shoppingCartRequestDTOMap. If the key is not present,
            it automatically computes the value using the provided
            lambda expression, which creates a new ShoppingCartRequestDTO
            object. The computed value is then returned.
            This approach eliminates the need for the
            containsKey check and separate put/get operations.
             */
    }
}
