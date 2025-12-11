package com.pluralsight.NorthwindTradersAPI.controller;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    //respond to link
    @RequestMapping
    public List<Products> getProducts {

        return Product;
    }

}
