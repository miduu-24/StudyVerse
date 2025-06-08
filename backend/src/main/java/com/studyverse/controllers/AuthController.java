package com.studyverse.controllers;

import com.studyverse.models.User;
import com.studyverse.repositories.UserRepository;
import com.studyverse.dto.LoginRequestDTO;
import com.studyverse.dto.RegisterRequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final String DEFAULT_AVATAR = "https://www.gravatar.com/avatar/?d=mp";

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
        String email = request.getEmail();
        String password = request.getPassword();
        String username = request.getUsername();

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email sau parolă lipsă"));
        }

        if (userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email deja folosit"));
        }

        if (userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username deja folosit"));
        }

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhotoPath(DEFAULT_AVATAR); // ✅ setăm o poză implicită
        user.setBackgroundPhotoPath(null); // poate fi null
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "Cont creat"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        String email = request.getEmail();
        String password = request.getPassword();

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(400).body(Map.of("message", "Email invalid"));
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(400).body(Map.of("message", "Parolă greșită"));
        }

        String fakeToken = UUID.randomUUID().toString();

        // ✅ Nu mai folosim Map.of — evităm crash la valori null
        Map<String, Object> userData = new HashMap<>();
        userData.put("email", user.getEmail());
        userData.put("username", user.getUsername());
        userData.put("photoPath", user.getPhotoPath()); // poate fi null
        userData.put("backgroundPhotoPath", user.getBackgroundPhotoPath()); // poate fi null
        userData.put("scoreMath", user.getScoreMath());
        userData.put("scoreChemistry", user.getScoreChemistry());

        Map<String, Object> response = new HashMap<>();
        response.put("token", fakeToken);
        response.put("user", userData);

        return ResponseEntity.ok(response);
    }
}
