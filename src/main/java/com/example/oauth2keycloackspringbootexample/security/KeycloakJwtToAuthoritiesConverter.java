package com.example.oauth2keycloackspringbootexample.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;

public class KeycloakJwtToAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String REALM_ACCESS_CLAIM = "realm_access";
    private static final String ROLES = "roles";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        var realmAccess = jwt.getClaimAsMap(REALM_ACCESS_CLAIM);
        var roles = (List<String>) realmAccess.get(ROLES);

        return roles.stream()
                .map(this::toGrantedAuthority)
                .toList();
    }

    private GrantedAuthority toGrantedAuthority(String value) {
        return new SimpleGrantedAuthority(value);
    }
}
