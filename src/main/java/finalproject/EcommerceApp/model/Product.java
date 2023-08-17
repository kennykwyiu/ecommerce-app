package finalproject.EcommerceApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "product")
@AllArgsConstructor
public class Product extends AbstractAuditableEntity<Product, Long> {
    @Column(name = "title")
    private String title;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "price", precision = 19, scale = 2)
    private BigDecimal price;

    @Column(name = "thumbnail")
    private String thumbnail;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

}
