package com.aleksic.medapp.controllers;

import com.aleksic.medapp.models.HealthCheck;
import com.aleksic.medapp.repositories.HealthCheckRepository;
import com.aleksic.medapp.repositories.UserRepository;
import com.aleksic.medapp.models.User;
import com.aleksic.medapp.services.HealthCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HealthCheckService healthCheckService;
//    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public @ResponseBody
    Iterable<User> getAllUsers () {

        return userRepository.findAll();
    }

    @GetMapping("/users/{id}/healthchecks")
    public List<HealthCheck> getUsersHealthChecks (@PathVariable Integer id) {
        return healthCheckService.getHealthChecksByUserId(id);
    }

    @GetMapping("/users/{id}")
    public User getUser (@PathVariable Integer id) {
        return userRepository.findById(id).get();
    }

    @PostMapping(path="/users")
    public String addNewUser (@RequestBody User user) {
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
