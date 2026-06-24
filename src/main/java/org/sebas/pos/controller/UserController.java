package org.sebas.pos.controller;

import org.sebas.pos.dto.UserDto;
import org.sebas.pos.mapper.UserMapper;
import org.sebas.pos.model.Users;
import org.sebas.pos.service.UserService;
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
    public ResponseEntity<Users> createUser(@RequestBody UserDto userDto) {
        Users user = userService.createUserCashier(userDto);
        return new ResponseEntity<Users>(user, HttpStatus.CREATED);

    }

    @GetMapping()
    public ResponseEntity<List<Users>> getUsers(){
        List<Users> users = userService.getUsers();
        return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        UserDto user = userMapper.toUserDto(userService.getUserByUsername(username));
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{username}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
