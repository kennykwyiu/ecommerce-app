package finalproject.EcommerceApp.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product")
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

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

//    @ManyToOne  // loop dead
//    @JoinColumn(name = "product_images_id")
//    private ProductImages productImages;

    @Builder
    public Product(String title,
                   Integer quantity,
                   String description,
                   BigDecimal price,
                   String thumbnail,
                   String countryOfOrigin,
                   String color,
                   ProductCategory productCategory) {
        this.title = title;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.thumbnail = thumbnail;
        this.countryOfOrigin = countryOfOrigin;
        this.color = color;
        this.productCategory = productCategory;

    }
}
