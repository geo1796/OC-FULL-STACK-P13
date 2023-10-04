package com.openclassrooms.yourcaryourway.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private String token;
    private Date expiry;
    private List<String> roles;
    private String username;
    private Integer userId;
}