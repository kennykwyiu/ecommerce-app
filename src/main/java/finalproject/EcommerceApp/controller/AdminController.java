package finalproject.EcommerceApp.controller;

import com.google.firebase.auth.FirebaseAuthException;
import finalproject.EcommerceApp.firebase.FirebaseAuthService;
import finalproject.EcommerceApp.model.Permission;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    FirebaseAuthService firebaseAuthService;

    @PostMapping("/grant")
    public ResponseEntity<Void> grantAdminPermission(@Valid @RequestBody AdminGrandRequestDTO adminGrandRequestDTO) throws FirebaseAuthException {
        String uid = adminGrandRequestDTO.getUid();
        List<Permission> permissions = adminGrandRequestDTO.getPermissions();
        firebaseAuthService.setUserClaims(uid, permissions);
    return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/test")

    public ResponseEntity<String> test(Principal principal) {
        String principalName = principal.getName();
        return ResponseEntity.ok(principalName);
    }
}
