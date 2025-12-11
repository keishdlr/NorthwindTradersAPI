package com.pluralsight.NorthwindTradersAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // this method will respond to http://localhost:8080/
    @GetMapping("/")
    public String helloWorld(@RequestParam(defaultValue = "World") String country){
        return "Hello "+ country;
    }

}
