package com.cmc.selfdevelopment.domain.user.entity;

import com.cmc.selfdevelopment.global.common.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;

import java.util.Collection;
import java.util.Objects;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@ToString
public class UserAccount extends BaseEntity implements UserDetails {
    String email;
    String password;
    String name;
    int age;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return getId().toString();
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
