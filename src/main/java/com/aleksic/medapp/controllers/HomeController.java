package com.aleksic.medapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class HomeController {
    @RequestMapping("/")
    public String index () {
        return "Hello from HomeController";
    }
}
