package org.sebas.pos;

import org.sebas.pos.features.user.dto.RegisterDto;
import org.sebas.pos.common.model.ROLE;
import org.sebas.pos.features.user.domain.Users;
import org.sebas.pos.features.user.service.UserService;
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
        RegisterDto userDto  = RegisterDto.builder()
                    .role(ROLE.ADMIN)
                    .username("sebas")
                    .password("123sebass")
            .build();
        Users finalUser = userService.createUserAdmin(userDto);
        System.out.println("Admin user registred consult dev for access");

    }
}
