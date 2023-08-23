package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.dto_request.PaymentRequestDTO;
import finalproject.EcommerceApp.dto_response.ShoppingOrderWrapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static finalproject.EcommerceApp.model.ShoppingOrderStatus.PAYMENT_SETTLED;

@Service
@Transactional
public class PaymentService {

    public void settlePayment(ShoppingOrderWrapper shoppingOrderWrapper) {
        BigDecimal orderTotal = shoppingOrderWrapper.getShoppingOrder().getTotal();
        Long shoppingOrderId = shoppingOrderWrapper.getShoppingOrder().getId();

        PaymentRequestDTO paymentRequestDTO = PaymentRequestDTO.builder()
                .shoppingOrderId(shoppingOrderId)
                        .orderTotal(orderTotal)
                                .build();

        processPayment(paymentRequestDTO);

        shoppingOrderWrapper.getShoppingOrder().setStatus(PAYMENT_SETTLED);
    }

    private void processPayment(PaymentRequestDTO paymentRequestDTO) {
        BigDecimal orderTotal = paymentRequestDTO.getOrderTotal();
        Long shoppingOrderId = paymentRequestDTO.getShoppingOrderId();
        System.out.printf("Payment: %s of ID:%s order was paid successfully", orderTotal, shoppingOrderId);
    }
}
