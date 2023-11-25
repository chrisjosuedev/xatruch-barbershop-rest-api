package dev.chrisjosue.xatruchbarbershopapi.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 20)
    private String title;

    @Column(nullable = false)
    @Size(max = 100)
    private String review;

    @Column(nullable = false, name = "is_approved")
    private boolean isApproved;

    @Column(nullable = false, name = "is_active")
    private boolean isActive;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
