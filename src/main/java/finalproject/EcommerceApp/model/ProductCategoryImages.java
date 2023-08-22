package finalproject.EcommerceApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@Table(name = "product_images")
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryImages extends AbstractAuditableEntity<ProductCategoryImages, Long> {
    @Column(name = "url")
    private String url;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

}
