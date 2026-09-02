package com.mvs.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String name;
    private String email;
    private String role;
    private boolean loggedIn;
    private String message;
}
