package org.sebas.pos.mapper;

import org.sebas.pos.dto.UserDto;
import org.sebas.pos.model.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public Users toUserEntity(UserDto userDto) {
        return Users.builder()
                .role(userDto.getRole())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
    }
    public UserDto toUserDto(Users user){
        return UserDto.builder()
                .role(user.getRole())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
