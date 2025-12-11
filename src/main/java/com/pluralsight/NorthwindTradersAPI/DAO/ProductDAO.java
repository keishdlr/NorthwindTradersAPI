package com.pluralsight.NorthwindTradersAPI.DAO;

import com.pluralsight.NorthwindTradersAPI.models.Product;

import java.util.List;

public interface ProductDAO {

    // This method will add a new Film to the database.
    // It is required because we are implementing the FilmDao interface.
    Product add(Product product);

    public static List<Product> getAll() {


            return List.of();
        }

        public static void add(Product product) {
        }

}
