package com.unfu.project.domain.authentication;

import com.unfu.project.domain.authentication.enumeration.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authority")
@Data
public class Authority implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 405683702606528608L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authority", unique = true)
    @Enumerated(EnumType.STRING)
    private Role authority;

    @Override
    public String getAuthority() {
        return authority.toString();
    }

    public static Authority fromRole(Role role) {
        var authority = new Authority();
        authority.setAuthority(role);
        return authority;
    }
}
