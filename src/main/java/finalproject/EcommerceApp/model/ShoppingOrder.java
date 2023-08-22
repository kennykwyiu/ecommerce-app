package finalproject.EcommerceApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@Entity
@Table(name = "shopping_order")
@AllArgsConstructor
public class ShoppingOrder extends AbstractAuditableEntity<ShoppingOrder, Long> {

    @Column(name = "total", precision = 19, scale = 2)
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "system_user_id")
    private SystemUser systemUser;

    @Enumerated(EnumType.STRING)
    private ShoppingOrderStatus status;

}
