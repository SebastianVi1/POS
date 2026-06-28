package org.sebas.pos.features.user.mapper;

import org.sebas.pos.features.user.dto.RegisterDto;
import org.sebas.pos.features.user.dto.UserDto;
import org.sebas.pos.features.user.domain.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public Users toUserEntity(RegisterDto userDto) {
        return Users.builder()
                .role(userDto.getRole())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
    }
    public UserDto toUserDto(Users user){
        return UserDto.builder()
                .id(user.getId())
                .role(user.getRole())
                .username(user.getUsername())
                .build();
    }
}
