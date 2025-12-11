package com.pluralsight.NorthwindTradersAPI.DAO;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDAO implements ProductDAO {

    // This is the DataSource that we will use to connect to the database.
    // The DataSource is created in our DbConfiguration class.
    private DataSource dataSource;

    // This is a constructor.
    // Spring will automatically call this constructor and pass in the DataSource.
    // The @Autowired annotation tells Spring to "inject" the DataSource Bean here.
    @Autowired
    public JdbcProductDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // This method will add a new product to the database.
    // It is required because we are implementing the productDao interface.
    @Override
    public Product add(Product product) {
        // This is the SQL INSERT statement we will run.
        String sql = "INSERT INTO product (productId, productName, categoryId, unitPrice) VALUES (?, ?, ?)";

        // This is a "try-with-resources" block.
        // It ensures that the Connection and PreparedStatement are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set the first parameter (?)
            stmt.setInt(1, product.getProductID());

            // Set the second parameter (?)
            stmt.setString(2, product.getProductName());

            stmt.setInt(3, product.getCategoryID());

            stmt.setDouble(4, product.getUnitPrice());

            // Hard-coding it to 1 because the categoryId cannot be NULL in the database.
            // We are not asking the user for this value in the UI yet.
            stmt.setInt(3, 1);

            // Execute the INSERT statement — this will add the row to the database.
            stmt.executeUpdate();

            // Retrieve the generated film_id
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int newId = keys.getInt(1);
                    product.setProductID(newId); // Set the generated ID on the Film object
                }
            }

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            e.printStackTrace();
        }

        return product;
    }

    // This method will return a list of all Films from the database.
    // It is required because we are implementing the FilmDao interface.
    @Override
    public List<Product> getAll() {
        // Create an empty list to hold the objects we will retrieve.
        List<Product> products = new ArrayList<>();

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
                Product product = new Product();

                // Set the film's ID from the "film_id" column.
                product.setProductID(rs.getInt("productID"));

                // Set the film's title from the "title" column.
                product.setProductName(rs.getString("productName"));

                // Set the film's rental rate from the "rental_rate" column.
                product.setCategoryID(rs.getInt("categoryID"));

                product.setUnitPrice(rs.getDouble("unitPrice"));

                // Add the Film object to our list.
                product.add(product);
            }

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            e.printStackTrace();
        }

        // Return the list of Film objects.
        return products;
    }
}
