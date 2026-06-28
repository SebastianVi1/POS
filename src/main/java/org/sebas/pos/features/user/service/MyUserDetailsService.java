package org.sebas.pos.features.user.service;

import org.sebas.pos.common.model.UserPrincipal;
import org.sebas.pos.features.user.domain.Users;
import org.sebas.pos.features.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.getUsersByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new UserPrincipal(user);

    }
}
