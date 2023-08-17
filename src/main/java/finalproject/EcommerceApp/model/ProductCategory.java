package finalproject.EcommerceApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@Table(name = "product_category")
@AllArgsConstructor
public class ProductCategory extends AbstractAuditableEntity<ProductCategory, Long> {
    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_description")
    private String categoryDescription;
}
