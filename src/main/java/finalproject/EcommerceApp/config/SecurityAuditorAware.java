package finalproject.EcommerceApp.config;

import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.repository.SystemUserRepository;
import finalproject.EcommerceApp.service.SystemUserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityAuditorAware implements AuditorAware<SystemUser> {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Optional<SystemUser> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();

        }
        String externalUserId = authentication.getName();

        entityManager.setFlushMode(FlushModeType.COMMIT);
        Optional<SystemUser>  opt = systemUserRepository.findByExternalUserId(externalUserId);
        entityManager.setFlushMode(FlushModeType.AUTO);
        return opt;
    }
}
