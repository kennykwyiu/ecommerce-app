package finalproject.EcommerceApp.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
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

    @Column(name = "active_address")
    private Byte activeAddress = 0;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "system_user_id")
    private SystemUser systemUser;

    @Builder
    public SystemUserAddress(LocalDateTime createdAt,
                             LocalDateTime updatedAt,
                             SystemUser createdBy,
                             SystemUser lastModifiedBy,
                             String room,
                             String flat,
                             String block,
                             String building,
                             String town,
                             String district,
                             String county,
                             String receiver,
                             String phoneNumber,
                             SystemUser systemUser) {
        this.room = room;
        this.flat = flat;
        this.block = block;
        this.building = building;
        this.town = town;
        this.district = district;
        this.county = county;
        this.receiver = receiver;
        this.phoneNumber = phoneNumber;
        this.systemUser = systemUser;
    }

    @Override
    public String toString() {
        return "ActiveUserAddress: [" +
                "room='" + room + '\'' +
                ", flat='" + flat + '\'' +
                ", block='" + block + '\'' +
                ", building='" + building + '\'' +
                ", town='" + town + '\'' +
                ", district='" + district + '\'' +
                ", county='" + county + '\'' +
                ", receiver='" + receiver + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ']';
    }
}
