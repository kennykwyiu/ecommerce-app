package finalproject.EcommerceApp.controller;
import finalproject.EcommerceApp.dto_request.SignUpRequestDTO;
import finalproject.EcommerceApp.dto_response.SystemUserRespondDTO;
import finalproject.EcommerceApp.exception.ExternalServiceException;
import finalproject.EcommerceApp.factory.SystemUserFactory;
import finalproject.EcommerceApp.firebase.FirebaseAuthService;
import finalproject.EcommerceApp.model.Permission;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.service.SystemUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SystemUserFactory systemUserFactory;
    @Autowired
    private FirebaseAuthService firebaseAuthService;

    @PostMapping("/signup")
    public ResponseEntity<SystemUserRespondDTO> signUp(@Valid @RequestBody SignUpRequestDTO requestDTO) throws ExternalServiceException {
        SystemUser systemUser = systemUserService.signUp(requestDTO);
        SystemUserRespondDTO systemUserRespondDTO = systemUserFactory.toDTO(systemUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(systemUserRespondDTO);
    }
    @PostMapping("/grant")
    public ResponseEntity<Void> grant(Principal principal) throws ExternalServiceException {
        String principalName = principal.getName();
        List<Permission> requestedPermissions = List.of(Permission.REGULAR_USER, Permission.ADMIN);
        firebaseAuthService.setUserClaims(principalName, requestedPermissions);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
