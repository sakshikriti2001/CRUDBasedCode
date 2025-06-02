package com.crud.code.hospital.service;
import com.crud.code.hospital.model.UserEntity;
import com.crud.code.hospital.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUserName(userName).orElseThrow(()->new UsernameNotFoundException("user not found with username: "+userName));

        SimpleGrantedAuthority grant = new SimpleGrantedAuthority("ROLE_"+user.getRole().name());
        return new User(
                user.getUserName(),
                user.getPassword(),
                Collections.singletonList(grant)
        );
    }
}
