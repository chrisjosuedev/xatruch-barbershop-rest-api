package dev.chrisjosue.xatruchbarbershopapi.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "persons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @Size(max = 64)
    private String fullName;

    @Column(name = "is_barber", nullable = false)
    private Boolean isBarber;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
