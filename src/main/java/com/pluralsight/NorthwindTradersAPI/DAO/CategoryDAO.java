package com.pluralsight.NorthwindTradersAPI.DAO;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import java.util.List;

public interface CategoryDAO {
    static List<Category> getAll() {

        return List.of();
    }

    static void add(Category category) {
    }
}
