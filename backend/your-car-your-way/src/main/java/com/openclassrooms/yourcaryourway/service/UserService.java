package com.openclassrooms.yourcaryourway.service;

import com.openclassrooms.yourcaryourway.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Integer id);
}
