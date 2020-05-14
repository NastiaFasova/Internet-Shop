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
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.util.ConnectionUtil;
import org.apache.log4j.Logger;

@Dao
public class BucketDaoJdbcImpl implements BucketDao {
    private static final Logger LOGGER = Logger.getLogger(BucketDaoJdbcImpl.class);

    @Override
    public Bucket create(Bucket bucket) {
        String query = "INSERT INTO buckets(user_id) VALUES (?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement
                    = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, bucket.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            bucket.setId(resultSet.getLong(1));
            addProductToBucket(bucket);
            LOGGER.info("The user is added into DB successfully");
            return bucket;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add the user" + e);
        }
    }

    @Override
    public Optional<Bucket> get(Long id) {
        String query = "SELECT * FROM buckets WHERE bucket_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Bucket bucket = null;
            while (resultSet.next()) {
                bucket = getBucketFromResultSet(resultSet);
                bucket.setProducts(getAllProductsFromBucket(bucket.getId()));
            }
            LOGGER.info("The user is get by ID successfully");
            return Optional.ofNullable(bucket);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get the bucket by its ID" + e);
        }
    }

    @Override
    public List<Bucket> getAll() {
        String query = "SELECT * FROM buckets";
        List<Bucket> buckets = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Bucket bucket = getBucketFromResultSet(resultSet);
                bucket.setProducts(getAllProductsFromBucket(bucket.getId()));
                buckets.add(bucket);
            }
            LOGGER.info("The information about all users is get successfully");
            return buckets;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all the buckets" + e);
        }
    }

    @Override
    public Bucket update(Bucket bucket) {
        String query = "UPDATE buckets SET user_id = ? WHERE bucket_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, bucket.getUserId());
            statement.setLong(2, bucket.getId());
            statement.executeUpdate();
            deleteBucketFromBucketsProducts(bucket.getId());
            addProductToBucket(bucket);
            LOGGER.info("The information about user is updated successfully");
            return bucket;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update the bucket" + e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM buckets WHERE bucket_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            LOGGER.info("The information about user is deleted successfully");
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete the bucket by its id" + e);
        }
    }

    private void addProductToBucket(Bucket bucket) throws SQLException {
        String query = "INSERT INTO buckets_products(bucket_id, product_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Product product : bucket.getProducts()) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, bucket.getId());
                statement.setLong(2, product.getId());
                statement.executeUpdate();
                LOGGER.info("The products are successfully added into buckets_products");
            }
        }
    }

    private Bucket getBucketFromResultSet(ResultSet resultSet) throws SQLException {
        long bucketId = resultSet.getLong("bucket_id");
        long userId = resultSet.getLong("user_id");
        Bucket bucket = new Bucket(userId);
        bucket.setId(bucketId);
        LOGGER.info("The object of bucket is successfully created");
        return bucket;
    }

    private List<Product> getAllProductsFromBucket(Long bucketId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT products.product_id, products.product_name,"
                + " products.price FROM buckets_products"
                + " INNER JOIN products USING (product_id) WHERE bucket_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, bucketId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long productId = resultSet.getLong("product_id");
                String name = resultSet.getString("product_name");
                BigDecimal price = resultSet.getBigDecimal("price");
                Product product = new Product(name, price);
                product.setId(productId);
                products.add(product);
            }
            LOGGER.info("The products are successfully got from the bucket");
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all products from bucket" + e);
        }
    }

    private void deleteBucketFromBucketsProducts(Long bucketId) {
        String query = "DELETE FROM buckets_products WHERE bucket_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, bucketId);
            statement.executeUpdate();
            LOGGER.info("The products are successfully deleted from the bucket");
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete bucket from buckets_products" + e);
        }
    }
}
