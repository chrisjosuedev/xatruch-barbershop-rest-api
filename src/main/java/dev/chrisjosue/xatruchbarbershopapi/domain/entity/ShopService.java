package dev.chrisjosue.xatruchbarbershopapi.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "barber_services")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_name", nullable = false)
    @Size(max = 30)
    private String serviceName;

    @Column(nullable = false)
    private Double price;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "barberService")
    private List<BookingTempCart> bookingTempCarts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "barberService")
    private List<BookingDetail> bookingDetails;
}
