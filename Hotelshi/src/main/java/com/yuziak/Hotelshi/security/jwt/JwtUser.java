package com.yuziak.Hotelshi.security.jwt;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yuziak.Hotelshi.entity.Room;

public class JwtUser implements UserDetails {
	
	
	private final Integer id;
    private final String username;
    private final String name;
    private final String password;
    private final Room room;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;
    
    public JwtUser(
            Integer id,
            String username,
            String name,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            Room room
    ) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
        this.room=room;
    }

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }


    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

	public boolean isEnabled() {
		return enabled;
	}

	public Room getRoom() {
		return room;
	}



}
