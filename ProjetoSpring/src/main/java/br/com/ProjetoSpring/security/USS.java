package br.com.ProjetoSpring.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class USS implements UserDetails {

    private Long id;
    private String nick;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nick;
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

    public USS(Long id, String nick, String password, String perfil) {
        this.id = id;
        this.nick = nick;
        this.password = password;
        this.authorities = Arrays.asList(new SimpleGrantedAuthority(perfil));
    }
}
