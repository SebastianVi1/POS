package org.sebas.pos.features.auth.dto;

import lombok.Data;
import org.sebas.pos.features.user.dto.UserDto;

@Data
public class AuthResponseDto {
    private UserDto user;
    private String accessToken;
    private String refreshToken;
}
