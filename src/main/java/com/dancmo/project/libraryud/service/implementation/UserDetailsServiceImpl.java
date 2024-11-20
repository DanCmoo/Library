package com.dancmo.project.libraryud.service.implementation;


import com.dancmo.project.libraryud.persistence.entity.Cliente;
import com.dancmo.project.libraryud.persistence.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

private final ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByCorreo(username).orElseThrow(() -> new UsernameNotFoundException("No user found with e-mail: " + username));
        Collection<? extends GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + cliente.getRole()));
        return new User(cliente.getCorreo(), cliente.getPassword(),true,true,true,true,authorities);
    }
}
