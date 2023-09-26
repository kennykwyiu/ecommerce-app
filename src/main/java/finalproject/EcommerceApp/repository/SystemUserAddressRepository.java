package finalproject.EcommerceApp.repository;

import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.model.SystemUserAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SystemUserAddressRepository extends AbstractBaseRepository<SystemUserAddress, Long> {
    List<SystemUserAddress> findBySystemUser(SystemUser systemUser);

    @Query(value = "select * from system_user_address a " +
            "where active_address = 1 and system_user_id = ?", nativeQuery = true)
    SystemUserAddress findBySystemUserAndActiveAddress(Long systemUserId);



}
