package finalproject.EcommerceApp.controller;

import com.google.firebase.auth.FirebaseAuthException;
import finalproject.EcommerceApp.dto_request.AdminGrandRequestDTO;
import finalproject.EcommerceApp.exception.ExternalServiceException;
import finalproject.EcommerceApp.exception.ResourceNotFoundException;
import finalproject.EcommerceApp.firebase.FirebaseAuthService;
import finalproject.EcommerceApp.model.Permission;
import finalproject.EcommerceApp.model.SystemUser;
import finalproject.EcommerceApp.service.SystemUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private FirebaseAuthService firebaseAuthService;

    @Autowired
    private SystemUserService systemUserService;

    @PostMapping("/grant")
    public ResponseEntity<Void> grantAdminPermission(@Valid @RequestBody AdminGrandRequestDTO adminGrandRequestDTO) throws ExternalServiceException {
        String uid = adminGrandRequestDTO.getUid();
        List<Permission> permissions = adminGrandRequestDTO.getPermissions();
        firebaseAuthService.setUserClaims(uid, permissions);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/delete/{systemUserId}") // OK
    public ResponseEntity<Void> deleteUser(@PathVariable("systemUserId") Long systemUserId) throws ResourceNotFoundException, FirebaseAuthException {
        SystemUser systemUser = systemUserService.findById(systemUserId);
        String externalUserId = systemUser.getExternalUserId();
        firebaseAuthService.deleteUser(externalUserId);
        systemUserService.delete(systemUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/test")

    public ResponseEntity<String> test(Principal principal) {
        String principalName = principal.getName();
        return ResponseEntity.ok(principalName);
    }
}
