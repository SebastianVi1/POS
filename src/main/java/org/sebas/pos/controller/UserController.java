package org.sebas.pos.controller;

import org.sebas.pos.dto.UserDto;
import org.sebas.pos.model.Users;
import org.sebas.pos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UserDto userDto) {
        Users user = userService.createUser(userDto);
        return new ResponseEntity<Users>(user, HttpStatus.CREATED);

    }

}
