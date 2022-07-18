package com.oze.security;

import com.oze.staff.Staff;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public class CustomUserPrincipal implements CustomUserDetails {

    private static final long serialVersionUID = 1L;

    private final Staff staff;

    public CustomUserPrincipal(Staff staff) {
        this.staff = staff;
    }

    public Staff getStaff() {
        return staff;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return staff.getName();
    }

    @Override
    public String getUsername() {
        return staff.getUuid();
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
