package com.pluralsight.NorthwindTradersAPI.DAO;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCategoryDAO implements CategoryDAO {

    // This is the DataSource that we will use to connect to the database.
    // The DataSource is created in our DbConfiguration class.
    private DataSource dataSource;

    // This is a constructor.
    // Spring will automatically call this constructor and pass in the DataSource.
    // The @Autowired annotation tells Spring to "inject" the DataSource Bean here.
    @Autowired
    public JdbcCategoryDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // This method will add a new product to the database.
    // It is required because we are implementing the productDao interface.
    @Override
    public Category add(Category category) {
        // This is the SQL INSERT statement we will run.
        String sql = "INSERT INTO product (categoryId, categoryName) VALUES (?, ?)";

        // This is a "try-with-resources" block.
        // It ensures that the Connection and PreparedStatement are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set the first parameter (?)
            stmt.setInt(1, category.getCategoryID());

            // Set the second parameter (?)
            stmt.setString(2, category.getCategoryName());

            // Hard-coding it to 1 because the categoryId cannot be NULL in the database.
            // We are not asking the user for this value in the UI yet.
            stmt.setInt(1, 1);

            // Execute the INSERT statement — this will add the row to the database.
            stmt.executeUpdate();

            // Retrieve the generated film_id
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int newId = keys.getInt(1);
                    category.setCategoryID(newId); // Set the generated ID on the Film object
                }
            }

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            e.printStackTrace();
        }

        return category;
    }

    // This method will return a list of all Films from the database.
    // It is required because we are implementing the FilmDao interface.
    @Override
    public List<Category> getAll() {
        // Create an empty list to hold the objects we will retrieve.
        List<Category> categories = new ArrayList<>();

        // This is the SQL SELECT statement we will run.
        String sql = "SELECT productID, productName, categoryID, unitPrice FROM product";

        // This is a "try-with-resources" block.
        // It ensures that the Connection, Statement, and ResultSet are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Loop through each row in the ResultSet.
            while (rs.next()) {
                // Create a new Film object.
                Category category = new Category();

                category.setCategoryID(rs.getInt("categoryID"));

                category.setCategoryName(rs.getString("categoryName"));

                categories.add(category);
            }

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            e.printStackTrace();
        }

        // Return the list of Film objects.
        return categories;
    }
}
