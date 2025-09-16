package com.avishakar.dighanchi.mscitportal.service;

import com.avishakar.dighanchi.mscitportal.model.User;
import com.avishakar.dighanchi.mscitportal.repository.UserRepository;
import com.avishakar.dighanchi.mscitportal.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); 
        // if no users exist → make this user ADMIN
        if (userRepository.count() == 0) {
            user.setRole("ADMIN");
        } else {
            user.setRole("STUDENT"); // or STAFF, based on your needs
        }

        return userRepository.save(user);
    }
    public String login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            return jwtUtil.generateToken(email, userOpt.get().getRole());
        }
        throw new RuntimeException("Invalid credentials");
    }
}
