package dev.chrisjosue.xatruchbarbershopapi.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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
    private Boolean isApproved;

    @Column(nullable = false, name = "is_active")
    private Boolean isActive;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object object) {
        if (object instanceof Review reviewObj) {
            return this.id.equals(reviewObj.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
