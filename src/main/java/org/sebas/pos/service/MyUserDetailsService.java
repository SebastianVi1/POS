package org.sebas.pos.service;

import org.sebas.pos.model.UserPrincipal;
import org.sebas.pos.model.Users;
import org.sebas.pos.repo.UserRepo;
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
        Users user = userRepo.getUsersByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new UserPrincipal(user);

    }
}
