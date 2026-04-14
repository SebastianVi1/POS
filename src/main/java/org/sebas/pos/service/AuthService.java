package org.sebas.pos.service;

import org.sebas.pos.config.JwtTokenProvider;
import org.sebas.pos.dto.AuthResponseDto;
import org.sebas.pos.dto.LoginDto;
import org.sebas.pos.model.UserPrincipal;
import org.sebas.pos.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authManager;

    @Autowired
    UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    public AuthResponseDto loginUser(LoginDto loginDto){

        // used to authenticate the user
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()

                )
        );

          /* 02 - SecurityContextHolder is used to allows the rest of the application to know
        that the user is authenticated and can use user data from Authentication object */
        SecurityContextHolder.getContext().setAuthentication((authentication));

        String token = jwtTokenProvider.generateToken(authentication);

        //return token to the controller
        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        return response;


    }

}
