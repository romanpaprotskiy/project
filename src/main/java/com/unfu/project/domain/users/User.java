package com.unfu.project.domain.users;

import com.unfu.project.domain.authentication.Authority;
import com.unfu.project.domain.users.enumeration.Gender;
import com.unfu.project.domain.authentication.enumeration.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = -442253584073640320L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "phone")
    private String phone;

    @Column(name = "skype_id")
    private String skypeId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "active")
    private Boolean active = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    @EqualsAndHashCode.Exclude
    private Set<Authority> authorities = new HashSet<>();

    public User(User user) {
        this.id = user.getId();
        this.pictureUrl = user.getPictureUrl();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gender = user.getGender();
        this.birthDate = user.getBirthDate();
        this.phone = user.getPhone();
        this.skypeId = user.getSkypeId();
        this.email = user.getEmail();
        this.userId = user.getUserId();
        this.active = user.getActive();
        this.authorities = user.authorities;
    }

    public static User fromId(Long userId) {
        if (Objects.isNull(userId)) return null;
        User user = new User();
        user.setId(userId);
        return user;
    }

    public void addAuthority(Role role) {
        var authority = Authority.fromRole(role);
        authorities.add(authority);
    }

    public void addAuthority(Authority authority) {
        if (authority != null)
            authorities.add(authority);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
