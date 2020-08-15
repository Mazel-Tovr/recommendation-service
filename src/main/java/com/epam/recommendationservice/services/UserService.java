package com.epam.recommendationservice.services;


import com.epam.recommendationservice.models.User;
import com.epam.recommendationservice.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User loadUserByUsername(String username) throws NotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new NotFoundException("No user found. Username tried: " + username);
        }
        return new User();
    }
}
