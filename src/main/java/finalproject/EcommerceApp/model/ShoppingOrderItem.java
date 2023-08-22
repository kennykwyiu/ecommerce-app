package finalproject.EcommerceApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "shopping_order_item")
@AllArgsConstructor
public class ShoppingOrderItem extends AbstractAuditableEntity<ShoppingOrderItem, Long>{
    @ManyToOne
    @JoinColumn(name = "shopping_order_id")
    private ShoppingOrder shoppingOrder;

    @ManyToOne
    @JoinColumn(name = "product_snapshot_id")
    private ProductSnapshot productSnapshot;

    @Column(name = "quantity")
    private Integer quantity;

}
