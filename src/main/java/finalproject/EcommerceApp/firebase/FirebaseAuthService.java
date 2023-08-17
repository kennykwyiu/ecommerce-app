package finalproject.EcommerceApp.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import finalproject.EcommerceApp.dto_request.SignUpRequestDTO;
import finalproject.EcommerceApp.model.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FirebaseAuthService {

    @Autowired
    private FirebaseAuth firebaseAuth;

    public String createUser(SignUpRequestDTO requestDTO) throws FirebaseAuthException {
        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();

        createRequest.setDisplayName(requestDTO.getFullName())
                .setEmail(requestDTO.getEmail())
                .setPassword(requestDTO.getPassword());

        UserRecord userRecord = firebaseAuth.createUser(createRequest);

        String uid = userRecord.getUid();
        List<Permission> requestedPermissions = List.of(Permission.REGULAR_USER);
        setUserClaims(uid, requestedPermissions);

        log.info("Created user [{}] for [{}]",
                uid,
                userRecord.getEmail());

        return uid;

    }

    public void setUserClaims(String uid, List<Permission> requestedPermissions) throws FirebaseAuthException {
        List<String> permissions = requestedPermissions.stream()
                .map(Enum::toString)
                .toList();

        Map<String, Object> claims = Map.of("custom_claims", permissions);
        firebaseAuth.setCustomUserClaims(uid, claims);
    }


}
