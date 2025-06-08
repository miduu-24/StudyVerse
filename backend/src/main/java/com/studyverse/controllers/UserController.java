package com.studyverse.controllers;

import com.studyverse.dto.UserDTO;
import com.studyverse.exceptions.UserNotFoundException;
import com.studyverse.models.User;
import com.studyverse.services.UserService;
import com.studyverse.dto.AvatarUpdateDTO;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint pentru obținerea tuturor utilizatorilor
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    // Endpoint pentru crearea unui utilizator
    @PostMapping
    public User createUser(@RequestBody @Valid UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); // Poți adăuga criptare a parolei
        user.setEmail(userDTO.getEmail());
        user.setScoreMath(userDTO.getScoreMath());
        user.setScoreChemistry(userDTO.getScoreChemistry());
        user.setPhotoPath(userDTO.getPhotoPath());

        return userService.save(user);
    }

    // Endpoint pentru obținerea unui utilizator după ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    // Endpoint pentru actualizarea unui utilizator
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        User user = userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); // Poți adăuga criptare a parolei
        user.setEmail(userDTO.getEmail());
        user.setScoreMath(userDTO.getScoreMath());
        user.setScoreChemistry(userDTO.getScoreChemistry());
        user.setPhotoPath(userDTO.getPhotoPath());
        user.setBackgroundPhotoPath(userDTO.getBackgroundPhotoPath());

        return userService.save(user);
    }

    // Endpoint pentru ștergerea unui utilizator
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    // Endpoint pentru actualizarea avatarului
    @PutMapping("/avatar")
    public void updateAvatar(@RequestBody AvatarUpdateDTO dto) {
        User user = userService.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found with email " + dto.getEmail()));

        user.setPhotoPath(dto.getAvatarUrl());
        userService.save(user);
    }

    // Endpoint pentru actualizarea fundalului
    @PutMapping("/background")
    public void updateBackground(@RequestBody AvatarUpdateDTO dto) {
        User user = userService.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found with email " + dto.getEmail()));

        user.setBackgroundPhotoPath(dto.getBackgroundPhotoUrl());
        userService.save(user);
    }

    @PostMapping("/score")
    public ResponseEntity<String> updateUserScore(
            @RequestParam Long userId,
            @RequestParam String subject,
            @RequestParam int score // e.g., 10
    ) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        switch (subject.toLowerCase()) {
            case "matematica" -> user.setScoreMath(user.getScoreMath() + score);
            case "chimie" -> user.setScoreChemistry(user.getScoreChemistry() + score);
            default -> throw new IllegalArgumentException("Subiect necunoscut: " + subject);
        }

        userService.save(user);
        return ResponseEntity.ok("Scor actualizat");
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
