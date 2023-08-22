package finalproject.EcommerceApp.config;

import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.repository.SystemUserRepository;
import finalproject.EcommerceApp.service.SystemUserService;
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

    @Override
    public Optional<SystemUser> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();

        }
        String externalUserId = authentication.getName();

        return systemUserRepository.findByExternalUserId(externalUserId);


    }
}
