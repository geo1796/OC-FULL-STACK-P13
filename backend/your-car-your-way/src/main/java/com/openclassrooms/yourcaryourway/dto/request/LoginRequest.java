package com.openclassrooms.yourcaryourway.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class LoginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
