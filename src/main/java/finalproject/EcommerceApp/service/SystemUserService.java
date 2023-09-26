package finalproject.EcommerceApp.service;

import finalproject.EcommerceApp.dto_request.SignUpRequestDTO;
import finalproject.EcommerceApp.exception.ExternalServiceException;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.factory.SystemUserFactory;
import finalproject.EcommerceApp.firebase.FirebaseAuthService;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.repository.SystemUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Transactional
public class SystemUserService extends AbstractBaseService<SystemUser, Long> {

    @Autowired
    private SystemUserFactory systemUserFactory;

    @Autowired
    private FirebaseAuthService firebaseAuthService;

    private final SystemUserRepository repository;

    public SystemUserService(SystemUserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public SystemUser signUp(SignUpRequestDTO requestDTO) throws ExternalServiceException {
        String externalUserId = firebaseAuthService.createUser(requestDTO);
        SystemUser systemUser = systemUserFactory.toEntity(requestDTO);
        systemUser.setExternalUserId(externalUserId);
        return save(systemUser); // due to extends AbstractBaseService<SystemUser, Long>
    }


    public SystemUser findByExternalUserId(String externalUserId) throws ResourceNotFoundException {
        return repository.findByExternalUserId(externalUserId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("User not found by external User Id [%s]", externalUserId)
                ));
    }

    public SystemUser getReferenceById(Long systemUserId) {
        return repository.getReferenceById(systemUserId);
    }
}
