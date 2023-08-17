package finalproject.EcommerceApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "product")
@NoArgsConstructor
public class ProductImages extends AbstractAuditableEntity<ProductImages, Long> {
    @Column(name = "url")
    private String url;

}
