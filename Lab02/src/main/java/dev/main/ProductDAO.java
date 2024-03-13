package dev.main;




import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository<Product, Long> {

    private final Connection connection;
    private final String SQL_SELECT_ALL = "SELECT * FROM products";
    private final  String SQL_SELECT_BY_ID = "SELECT * FROM products WHERE id = ?";
    private final String SQL_INSERT = "INSERT INTO products(name, price) VALUES(?, ?)";
    private final String SQL_UPDATE = "UPDATE products SET name = ?, price = ? WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM products WHERE id = ?";

    public ProductDAO(String connectionString) throws SQLException {
        this.connection = DriverManager.getConnection(connectionString);
    }

    @Override
    public Long add(Product item) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT);
            statement.setString(1, item.getName());
            statement.setLong(2, item.getPrice());
            Long id = statement.getGeneratedKeys().getLong(1);
            return id;
        } catch (Exception var4) {
            System.out.println(var4);
            return null;
        }
    }

    @Override
    public List<Product> readAll() {
        List<Product> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet data = preparedStatement.executeQuery();
            while (data.next()) {
                Product product = new Product();
                product.setId(data.getLong("id"));
                product.setName(data.getString("name"));
                product.setPrice(data.getLong("price"));
                result.add(product);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public Product read(Long id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setLong(1, id);
            Product product = null;
            ResultSet data = statement.executeQuery();
            if (data.next()) {
                product = new Product(id, data.getString("name"), data.getLong("price"));
            }
            return product;
        } catch (Exception var6) {
            System.out.println(var6);
            return null;
        }
    }

    @Override
    public boolean update(Product item) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, item.getName());
            statement.setLong(2, item.getPrice());
            statement.setLong(3, item.getId());
            statement.executeUpdate();
            return true;
        } catch (Exception var4) {
            System.out.println(var4);
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (Exception var4) {
            System.out.println(var4);
            return false;
        }
    }
}
