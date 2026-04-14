package org.sebas.pos.dto;

import lombok.Builder;
import lombok.Data;
import org.sebas.pos.model.ROLE;


@Data
@Builder
public class UserDto {
    private String username;
    private String password;
    private ROLE role;
}
