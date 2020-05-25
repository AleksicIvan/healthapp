package com.aleksic.medapp.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class HomeController {
    @RequestMapping("/")
    public String index () {
        return "Hello from HomeController";
    }
}
