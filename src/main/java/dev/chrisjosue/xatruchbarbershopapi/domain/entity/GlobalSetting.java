package dev.chrisjosue.xatruchbarbershopapi.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "settings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @Column(name = "start_availability", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date startDailyAvailability;

    @Column(name = "end_availability", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date endDailyAvailability;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
