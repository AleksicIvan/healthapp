package com.aleksic.medapp.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "Hello from HomeController";
    }
}
