package dev.chrisjosue.xatruchbarbershopapi.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "barbers")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Barber extends User {
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date hired;
}
