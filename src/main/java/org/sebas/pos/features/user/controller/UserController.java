package org.sebas.pos.features.user.controller;

import jakarta.validation.Valid;
import org.sebas.pos.features.user.dto.RegisterDto;
import org.sebas.pos.features.user.dto.UserDto;
import org.sebas.pos.features.user.mapper.UserMapper;
import org.sebas.pos.features.user.domain.Users;
import org.sebas.pos.features.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid RegisterDto userDto) {
        Users user = userService.createUserCashier(userDto);
        return new ResponseEntity<>(userMapper.toUserDto(user), HttpStatus.CREATED);

    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers(){
        List<Users> users = userService.getUsers();
        List<UserDto> response = users.stream().map(userMapper::toUserDto).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        UserDto user = userMapper.toUserDto(userService.getUserByUsername(username));
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
