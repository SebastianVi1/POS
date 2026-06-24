package org.sebas.pos.service;

import org.sebas.pos.dto.UserDto;
import org.sebas.pos.exception.UsernameInUseException;
import org.sebas.pos.exception.UsernameNotFoundException;
import org.sebas.pos.mapper.UserMapper;
import org.sebas.pos.model.ROLE;
import org.sebas.pos.model.Users;
import org.sebas.pos.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder encoder;

    public Users createUserCashier(UserDto userDto){
        Users user = userMapper.toUserEntity(userDto);

        if (userRepo.getUsersByUsername(user.getUsername()).isPresent()){
            throw new UsernameInUseException("The username is already in use");
        }
        if (user.getRole() == null || user.getRole().name().isEmpty()){
            // normal user role by default
            user.setRole(ROLE.CASHIER);
        }
        user.setPassword(encoder.encode(userDto.getPassword()));
        //
         return userRepo.save(user);

    }

    public Users createUserAdmin(UserDto userDto){
        Users user = userMapper.toUserEntity(userDto);

        if (userRepo.getUsersByUsername(user.getUsername()).isPresent()){
            throw new UsernameInUseException("The username is already in use");
        }
        if (user.getRole() == null || user.getRole().name().isEmpty()){
            // normal user role by default
            user.setRole(ROLE.ADMIN);
        }
        user.setPassword(encoder.encode(userDto.getPassword()));
        //
        return userRepo.save(user);

    }

    public Users getUserByUsername(String username){
       Optional<Users> user =  userRepo.getUsersByUsername(username);
       if (user.isEmpty()){
           throw new UsernameNotFoundException("User not found with username: " + username);
       }
       return user.get();
    }


    public void deleteUser(UUID id) {
       Users user = userRepo.findById(id)
           .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
       userRepo.delete(user);
    }

    public void deleteAllUsers() {
        userRepo.deleteAll();
    }
}
