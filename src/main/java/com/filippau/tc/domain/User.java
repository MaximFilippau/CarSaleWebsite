package com.filippau.tc.domain;

import com.filippau.tc.validation.UniqueEmail;
import com.filippau.tc.validation.UniqueUsername;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@UniqueEmail(message = "This E-mail is already taken")
@UniqueUsername(message = "This username is already taken")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    private boolean active;

    private String activationCode;

    @OneToMany(mappedBy = "carOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Car> userCars;


    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
        return active;
    }
}
