package finalproject.EcommerceApp.service;

import com.google.firebase.auth.FirebaseAuthException;
import finalproject.EcommerceApp.dto_request.SignUpRequestDTO;
import finalproject.EcommerceApp.factory.SystemUserFactory;
import finalproject.EcommerceApp.firebase.FirebaseAuthService;
import finalproject.EcommerceApp.model.Permission;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.repository.SystemUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class SystemUserService extends AbstractBaseService<SystemUser, Long> {

    @Autowired
    private SystemUserFactory systemUserFactory;

    @Autowired
    private FirebaseAuthService firebaseAuthService;

    public SystemUser signUp(SignUpRequestDTO requestDTO) throws FirebaseAuthException {
        String externalUserId = firebaseAuthService.createUser(requestDTO);
        SystemUser systemUser = systemUserFactory.toEntity(requestDTO);
        systemUser.setExternalUserId(externalUserId);
        return save(systemUser); // due to extends AbstractBaseService<SystemUser, Long>
    }


}
