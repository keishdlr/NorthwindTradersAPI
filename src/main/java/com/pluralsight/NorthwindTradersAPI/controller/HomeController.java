package com.pluralsight.NorthwindTradersAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // this method will respond to http://localhost:8080/
    @GetMapping("/")
    public String homePage(@RequestParam(defaultValue="World") String name, @RequestParam(defaultValue="Red") String favcolor) {
        return "hello " + name +"! and your favorite color is " + favcolor;
    }


    // this method will respond to http://localhost:8080/
    @GetMapping("/eric")
    public String simba() {
        return "this is movie magic!";
    }

    // this method will respond to http://localhost:8080/
    @GetMapping("/AJ")
    public String AJ() {
        return "this is fake magic!";
    }


}
