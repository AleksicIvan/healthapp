package com.aleksic.medapp.controllers;

import com.aleksic.medapp.models.HealthCheck;
import com.aleksic.medapp.repositories.UserRepository;
import com.aleksic.medapp.models.User;
import com.aleksic.medapp.services.CustomerUserDetailsService;
import com.aleksic.medapp.services.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HealthCheckService healthCheckService;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;


    @GetMapping("/users")
    public @ResponseBody
    Iterable<User> getAllUsers () {

        return userRepository.findAll();
    }

    @GetMapping("/me")
    public @ResponseBody User returnLoggedUser () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        return user;
    }

    @GetMapping("/users/{id}/healthchecks")
    public ResponseEntity<Map<String, Object>> getUsersHealthChecks (@PathVariable Integer id,
                                                                   @RequestParam(defaultValue = "0") Integer pageNo,
                                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                                   @RequestParam(defaultValue = "createdAt") String sortBy) {
//        List<Object> healthChecksLite = new ArrayList<>();
//        Map<String, Object> allHealthChecks = healthCheckService.getAllHealthChecks(pageNo, pageSize, sortBy);
//        allHealthChecks.forEach(check -> {
//            healthChecksLite.add(healthCheckService.convertHealthcheckToMap(check));
//        });
        Map<String, Object> userHealthChecks = healthCheckService.getHealthChecksByUserId(id, pageNo, pageSize, sortBy);
        return new ResponseEntity(userHealthChecks, new HttpHeaders(), HttpStatus.OK);
//        return healthChecksLite;
    }

    @GetMapping("/users/{id}")
    public User getUser (@PathVariable Integer id) {
        return userRepository.findById(id).get();
    }

    @PostMapping(path="/users")
    public ResponseEntity<User> addNewUser (@RequestBody User user) {
        User loggedInUser = customerUserDetailsService.saveUser(user);
        return new ResponseEntity(loggedInUser, new HttpHeaders(), HttpStatus.CREATED);
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
