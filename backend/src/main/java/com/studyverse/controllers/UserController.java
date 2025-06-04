package com.studyverse.controllers;

import com.studyverse.dto.UserDTO;
import com.studyverse.exceptions.UserNotFoundException;
import com.studyverse.models.User;
import com.studyverse.services.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
        user.setPassword(userDTO.getPassword());  // Poți adăuga criptare a parolei
        user.setEmail(userDTO.getEmail());
        user.setScoreMath(userDTO.getScoreMath());
        user.setScoreChemistry(userDTO.getScoreChemistry());
        user.setProblemHistory(userDTO.getProblemHistory());

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
        User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); // Poți adăuga criptare a parolei
        user.setEmail(userDTO.getEmail());
        user.setScoreMath(userDTO.getScoreMath());
        user.setScoreChemistry(userDTO.getScoreChemistry());
        user.setProblemHistory(userDTO.getProblemHistory());

        return userService.save(user);
    }

    // Endpoint pentru ștergerea unui utilizator
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}

