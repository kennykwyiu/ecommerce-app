package finalproject.EcommerceApp.repository;

import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.model.WuXing;
import org.springframework.stereotype.Repository;

@Repository
public interface WuXingRepository extends AbstractBaseRepository<WuXing, Long> {
    WuXing findBySystemUser(SystemUser systemUser);
}
