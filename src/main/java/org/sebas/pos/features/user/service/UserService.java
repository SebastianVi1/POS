package org.sebas.pos.features.user.service;

import org.sebas.pos.features.user.dto.RegisterDto;
import org.sebas.pos.common.exception.UsernameInUseException;
import org.sebas.pos.common.exception.UsernameNotFoundException;
import org.sebas.pos.common.model.ROLE;
import org.sebas.pos.features.user.domain.Users;
import org.sebas.pos.features.user.repo.UserRepo;
import org.sebas.pos.features.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Users createUserCashier(RegisterDto userDto){
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

    public Users createUserAdmin(RegisterDto userDto){
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
    public List<Users> getUsers(){
        return userRepo.findAll();
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
