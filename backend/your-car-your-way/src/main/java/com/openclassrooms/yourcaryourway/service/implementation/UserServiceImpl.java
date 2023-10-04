package com.openclassrooms.yourcaryourway.service.implementation;

import com.openclassrooms.yourcaryourway.model.User;
import com.openclassrooms.yourcaryourway.repository.UserRepository;
import com.openclassrooms.yourcaryourway.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }
}
