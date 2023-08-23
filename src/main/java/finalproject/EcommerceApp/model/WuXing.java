package finalproject.EcommerceApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "wu-xing")
public class WuXing extends AbstractAuditableEntity<WuXing, Long> {
    @Column(name = "birth_time")
    private String birthTime;

    @Column(name = "birth_day")
    private String birthDay;

    @Column(name = "birth_month")
    private String birthMonth;

    @Column(name = "birth_year")
    private String birthYear;

    @ManyToOne
    @JoinColumn(name = "system_user_id")
    private SystemUser systemUser;

}
