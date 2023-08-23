package finalproject.EcommerceApp.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "system_user_address")
public class SystemUserAddress extends AbstractAuditableEntity<SystemUserAddress, Long> {
    @Column(name = "room")
    private String room;

    @Column(name = "flat")
    private String flat;

    @Column(name = "block")
    private String block;

    @Column(name = "building")
    private String building;

    @Column(name = "town")
    private String town;

    @Column(name = "district")
    private String district;

    @Column(name = "county")
    private String county;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "system_user_id")
    private SystemUser systemUser;



}
