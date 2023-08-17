package finalproject.EcommerceApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "system_users")
public class SystemUser extends AbstractAuditableEntity<SystemUser, Long>{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "external_user_id")
    private String externalUserId;

    @Builder
    public SystemUser(Long id, String userName, String fullName, String email, String externalUserId) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.externalUserId = externalUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemUser that = (SystemUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
