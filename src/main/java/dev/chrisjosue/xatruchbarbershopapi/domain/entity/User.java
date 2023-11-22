package dev.chrisjosue.xatruchbarbershopapi.domain.entity;

import dev.chrisjosue.xatruchbarbershopapi.domain.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "Name is required.")
    @Length(min = 2, max = 64, message = "Length must be min 2 and max 64 characters.")
    private String fullName;

    @Column(nullable = false)
    @NotBlank(message = "Email is required.")
    @Length(min = 2, max = 64, message = "Length must be min 2 and max 64 characters.")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password is required.")
    private char[] password;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Role is required.")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return null;
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
