package org.sebas.pos;

import org.sebas.pos.dto.UserDto;
import org.sebas.pos.model.ROLE;
import org.sebas.pos.model.Users;
import org.sebas.pos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {
    @Autowired
    private UserService userService;

    // Make an admin user on the first run
    @Override
    public void run(String... args) throws Exception{
        UserDto userDto  = UserDto.builder()
                    .role(ROLE.ROLE_ADMIN)
                    .username("sebas")
                    .password("123sebass")
            .build();
        Users finalUser = userService.createUser(userDto);
        System.out.println("Admin user registred consult dev for access");

    }
}
