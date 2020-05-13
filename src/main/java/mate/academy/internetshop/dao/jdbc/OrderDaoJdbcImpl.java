package mate.academy.internetshop.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.util.ConnectionUtil;
import org.apache.log4j.Logger;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoJdbcImpl.class);

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders (user_id) VALUES (?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                Long orderId = resultSet.getLong(1);
                order.setId(orderId);
            }
            addProductsToOrder(order);
            return order;
        } catch (SQLException e) {
            LOGGER.error("User can't make the order" + e);
            throw new DataProcessingException("Can't make the order");
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        String query = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Order order = null;
            if (resultSet.next()) {
                order = getOrderFromResultSet(resultSet);
            }
            return Optional.ofNullable(order);
        } catch (SQLException e) {
            LOGGER.error("User can't get the order by its ID" + e);
            throw new DataProcessingException("Can't get the order by its ID");
        }
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM orders";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't get all the orders" + e);
            throw new DataProcessingException("Can't get all the orders");
        }
        return orders;
    }

    @Override
    public Order update(Order element) {
        String query = "UPDATE orders SET order_id = ?, user_id = ? WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, element.getId());
            statement.setLong(2, element.getUserId());
            statement.setLong(3, element.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can't update the order" + e);
            throw new DataProcessingException("Can't update the order");
        }
        return element;
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Can't delete the order" + e);
            throw new DataProcessingException("Can't delete the order");
        }
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        long orderId = resultSet.getLong("order_id");
        long userId = resultSet.getLong("user_id");
        Order order = new Order(userId);
        order.setId(orderId);
        order.setProducts(getAllProducts(orderId));
        return order;
    }

    private List<Product> getAllProducts(Long orderId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT products.product_id, products.product_name,"
                + " products.price FROM orders_products"
                + " INNER JOIN products USING (product_id) WHERE order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long productId = resultSet.getLong("product_id");
                String name = resultSet.getString("product_name");
                BigDecimal price = resultSet.getBigDecimal("price");
                Product product = new Product(name, price);
                product.setId(productId);
                products.add(product);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't get all products from order" + e);
            throw new DataProcessingException("Can't get all products from order");
        }
        return products;
    }

    private void addProductsToOrder(Order order) {
        String query = "INSERT INTO orders_products(order_id, product_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getId());
            for (Product product : order.getProducts()) {
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error("Can't add products to order" + e);
            throw new DataProcessingException("Can't add products to order");
        }
    }
}
