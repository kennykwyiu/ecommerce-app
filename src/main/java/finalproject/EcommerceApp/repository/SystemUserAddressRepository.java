package finalproject.EcommerceApp.repository;

import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.model.SystemUserAddress;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserAddressRepository extends AbstractBaseRepository<SystemUserAddress, Long> {
    SystemUserAddress findBySystemUser(SystemUser systemUser);

}
