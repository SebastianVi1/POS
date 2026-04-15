package org.sebas.pos.service;

import org.sebas.pos.dto.UserDto;
import org.sebas.pos.exception.UsernameInUseException;
import org.sebas.pos.mapper.UserMapper;
import org.sebas.pos.model.ROLE;
import org.sebas.pos.model.Users;
import org.sebas.pos.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder encoder;

    public Users createUser(UserDto userDto){
        Users user = userMapper.toUserEntity(userDto);

        if (userRepo.getUsersByUsername(user.getUsername()) != null){
            throw new UsernameInUseException("The username is already in use");
        }
        if (user.getRole() == null || user.getRole().name().isEmpty()){
            // normal user role by default
            user.setRole(ROLE.ROLE_USER);
        }
        user.setPassword(encoder.encode(userDto.getPassword()));
        //
         return userRepo.save(user);

    }

    public Users getUserByUsername(String username){
        return userRepo.getUsersByUsername(username);
    }
}
