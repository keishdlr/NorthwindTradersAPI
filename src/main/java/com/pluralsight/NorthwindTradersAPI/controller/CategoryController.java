package com.pluralsight.NorthwindTradersAPI.controller;

import com.pluralsight.NorthwindTradersAPI.DAO.CategoryDAO;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public class CategoryController {

    //respond to link
    @GetMapping("/api/films")
    public List<Category> getAllCategories(){

        return CategoryDAO.getAll();
    }

    @PostMapping("/api/films")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Category addCategories(@RequestBody Category category){

        return CategoryDAO.add(category);

    }

}
