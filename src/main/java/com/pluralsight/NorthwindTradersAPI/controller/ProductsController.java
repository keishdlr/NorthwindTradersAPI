package com.pluralsight.NorthwindTradersAPI.controller;

import com.pluralsight.NorthwindTradersAPI.DAO.ProductDAO;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {

    //respond to link
    @GetMapping("/api/films")
    public List<Product> getAllProducts(){

        return ProductDAO.getAll();
    }

    @PostMapping("/api/films")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product addFProduct(@RequestBody Product product){

        return ProductDAO.add(product);

    }

}
