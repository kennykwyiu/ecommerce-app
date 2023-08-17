package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.model.Product;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@Transactional
public class ProductService extends AbstractBaseService<Product, Long> {
}
