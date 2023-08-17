package finalproject.EcommerceApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class AbstractPersistableEntity<ID> implements Serializable {
    @Serial
    private static final long serialVersionUID = 121412385935246L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected ID id;

    @Version
    protected Long version;

}
