package mate.academy.shop.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.shop.dao.OrderDao;
import mate.academy.shop.exceptions.DataProcessingException;
import mate.academy.shop.lib.Dao;
import mate.academy.shop.model.Order;
import mate.academy.shop.model.Product;
import mate.academy.shop.util.ConnectionUtil;
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
            resultSet.next();
            order.setId(resultSet.getLong(1));
            addProductsToOrder(order);
            LOGGER.info("The order is made!");
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't make the order" + e);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        String query = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                LOGGER.info("The order is successfully retrieved by its ID");
                return Optional.of(order);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve the order by its ID" + e);
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
            LOGGER.info("The information about all orders is successfully retrieved!");
            return orders;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve all the orders" + e);
        }
    }

    @Override
    public Order update(Order order) {
        deleteOrderFromList(order);
        addProductsToOrder(order);
        LOGGER.info("The information about order is updated");
        return order;
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            LOGGER.info("The order is deleted");
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete the order" + e);
        }
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        long orderId = resultSet.getLong("order_id");
        long userId = resultSet.getLong("user_id");
        Order order = new Order(userId);
        order.setId(orderId);
        order.setProducts(getAllProducts(orderId));
        LOGGER.info("The object of order is created");
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
            LOGGER.info("All the products from the order are successfully retrieved");
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve all products from order" + e);
        }
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
            LOGGER.info("All the products are added to the order");
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add products to order" + e);
        }
    }

    private void deleteOrderFromList(Order order) {
        String query = "DELETE FROM orders_products WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getId());
            statement.executeUpdate();
            LOGGER.info("The products are successfully deleted from the order");
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete order from orders_products" + e);
        }
    }

    @Override
    public List<Order> getByUserId(Long userId) {
        String query = "SELECT * FROM orders WHERE user_id = ?";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                orders.add(order);
            }
            LOGGER.info("The information about all orders is successfully retrieved!");
            return orders;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve all the orders" + e);
        }
    }
}
