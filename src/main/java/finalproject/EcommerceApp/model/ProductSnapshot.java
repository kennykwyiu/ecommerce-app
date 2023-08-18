package finalproject.EcommerceApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@Entity
@Table(name = "product_snapshot")
@AllArgsConstructor
@NoArgsConstructor
public class ProductSnapshot extends AbstractAuditableEntity<ProductSnapshot, Long> {
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

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
