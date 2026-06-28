package org.sebas.pos.features.auth.service;

import org.sebas.pos.common.exception.BadRequestException;
import org.sebas.pos.features.auth.dto.AuthResponseDto;
import org.sebas.pos.features.auth.dto.LoginDto;
import org.sebas.pos.features.auth.dto.RefreshTokenDto;
import org.sebas.pos.features.user.dto.RegisterDto;
import org.sebas.pos.features.user.dto.UserDto;
import org.sebas.pos.features.user.mapper.UserMapper;
import org.sebas.pos.features.auth.domain.RefreshToken;
import org.sebas.pos.features.user.domain.Users;
import org.sebas.pos.features.auth.repo.RefreshTokenRepo;
import org.sebas.pos.features.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class AuthService {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProviderService jwtTokenProviderService;

    @Autowired
    private RefreshTokenRepo refreshTokenRepo;
    @Autowired
    private UserMapper userMapper;

    public AuthResponseDto loginUser(LoginDto loginDto){
        // 1. Authenticate credentials
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        // 2. Save in SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Generate tokens
        String accessToken = jwtTokenProviderService.generateToken(authentication);
        String refreshToken = jwtTokenProviderService.generateRefreshToken(authentication);

        // 4. Save refresh token in database
        Users user = userService.getUserByUsername(loginDto.getUsername());
        saveRefreshToken(refreshToken, user);

        // 5. Return response
        return buildAuthResponse(userMapper.toUserDto(user), accessToken, refreshToken);
    }

    public AuthResponseDto refreshToken(RefreshTokenDto dto) {
        // 1. Validate that token exists and is valid
        RefreshToken refreshTokenEntity = refreshTokenRepo.findByToken(dto.getToken());
        if (refreshTokenEntity == null || refreshTokenEntity.isRevoked()) {
            throw new BadRequestException("Refresh token invalid or revoked");
        }

        // 2. Validate JWT signature
        if (!jwtTokenProviderService.validateToken(dto.getToken())) {
            throw new BadRequestException("Refresh token expired or corrupted");
        }

        // 3. Validate that it's not expired
        if (LocalDate.now().isAfter(refreshTokenEntity.getExpiryDate())) {
            refreshTokenEntity.setRevoked(true);
            refreshTokenRepo.save(refreshTokenEntity);
            throw new BadRequestException("Refresh token expired");
        }

        // 4. Generate new access token
        String username = jwtTokenProviderService.getUsername(dto.getToken());
        Users user = userService.getUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(), null, java.util.Collections.emptyList()
        );
        String newAccessToken = jwtTokenProviderService.generateToken(authentication);

        // 5. Return new access token (reuse same refresh token)
        return buildAuthResponse(userMapper.toUserDto(user), newAccessToken, dto.getToken());
    }

    private void saveRefreshToken(String token, Users user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(token);
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(LocalDate.now().plusDays(7));
        refreshToken.setRevoked(false);
        refreshTokenRepo.save(refreshToken);
    }

    private AuthResponseDto buildAuthResponse(UserDto user, String accessToken, String refreshToken) {
        AuthResponseDto response = new AuthResponseDto();
        response.setUser(user);
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        return response;


    }

    public UserDto register(RegisterDto registerDto) {
        Users newUser = userService.createUserCashier(registerDto);
        return userMapper.toUserDto(newUser);
    }

}
