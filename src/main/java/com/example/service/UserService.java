package com.example.service;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;

@Service
public class UserService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUserById(Long id, User user){
        if (id != null) {
            User existingUser = userRepository.findById(id).orElse(null);
            if (existingUser != null) {
                existingUser.setUsername(user.getUsername());
                existingUser.setPassword(user.getPassword());
                existingUser.setEmail(user.getEmail());
                return userRepository.save(existingUser);
            }
        }
        return  null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }
}