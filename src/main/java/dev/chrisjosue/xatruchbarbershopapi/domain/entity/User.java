package dev.chrisjosue.xatruchbarbershopapi.domain.entity;

import dev.chrisjosue.xatruchbarbershopapi.common.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class User extends Person implements UserDetails {
    @Column(nullable = false)
    @Size(max = 64)
    private String email;

    @Column(nullable = false)
    @Size(max = 64)
    private String password;

    @Column(name = "profile_url")
    @Size(max = 255)
    private String profileUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false, name = "is_password_updated")
    private Boolean isPasswordUpdated;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<BookingTempCart> bookingTempCarts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
