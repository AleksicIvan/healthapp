package com.aleksic.medapp.controllers;

import com.aleksic.medapp.repositories.UserRepository;
import com.aleksic.medapp.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public @ResponseBody
    Iterable<User> getAllUsers () {

        return userRepository.findAll();
    }

    @PostMapping(path="/users")
    public @ResponseBody String addNewUser (@RequestBody User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Saved";
    }

    @PutMapping("/users/{id}")
    public User updateUser (@RequestBody User newUser, @PathVariable Integer id) {
        return userRepository
                .findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}
