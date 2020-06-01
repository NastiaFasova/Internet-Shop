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
import mate.academy.shop.dao.BucketDao;
import mate.academy.shop.exceptions.DataProcessingException;
import mate.academy.shop.lib.Dao;
import mate.academy.shop.model.Bucket;
import mate.academy.shop.model.Product;
import mate.academy.shop.util.ConnectionUtil;
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
            LOGGER.info("The bucket is added into DB successfully");
            return bucket;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add the bucket" + e);
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
                bucket.setProducts(getAllProductsFromBucket(bucket));
            }
            LOGGER.info("The bucket by its ID was retrieved successfully");
            return Optional.ofNullable(bucket);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve the bucket by its ID" + e);
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
                bucket.setProducts(getAllProductsFromBucket(bucket));
                buckets.add(bucket);
            }
            LOGGER.info("The information about all buckets was retrieved successfully");
            return buckets;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve all the buckets" + e);
        }
    }

    @Override
    public Bucket update(Bucket bucket) {
        deleteBucketFromList(bucket.getId());
        addProductToBucket(bucket);
        LOGGER.info("The information about bucket is updated successfully");
        return bucket;
    }

    @Override
    public boolean delete(Long id) {
        deleteBucketFromList(id);
        String query = "DELETE FROM buckets WHERE bucket_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            LOGGER.info("The information about bucket was deleted successfully");
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete the bucket by its id" + e);
        }
    }

    private void addProductToBucket(Bucket bucket) {
        String query = "INSERT INTO buckets_products(bucket_id, product_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Product product : bucket.getProducts()) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, bucket.getId());
                statement.setLong(2, product.getId());
                statement.executeUpdate();
                LOGGER.info("The products are successfully added into buckets_products");
            }
        } catch (SQLException e) {
            throw new DataProcessingException("The products can't be added" + e);
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

    private List<Product> getAllProductsFromBucket(Bucket bucket) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT products.product_id, products.product_name,"
                + " products.price FROM buckets_products"
                + " INNER JOIN products USING (product_id) WHERE bucket_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, bucket.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long productId = resultSet.getLong("product_id");
                String name = resultSet.getString("product_name");
                BigDecimal price = resultSet.getBigDecimal("price");
                Product product = new Product(name, price);
                product.setId(productId);
                products.add(product);
            }
            LOGGER.info("The products are successfully retrieved from the bucket");
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve all products from bucket" + e);
        }
    }

    private void deleteBucketFromList(Long bucketId) {
        String query = "DELETE FROM buckets_products WHERE bucket_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, bucketId);
            statement.executeUpdate();
            LOGGER.info("The bucket is successfully deleted from the buckets_products");
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete bucket from buckets_products" + e);
        }
    }

    @Override
    public Optional<Bucket> getByUserId(Long userId) {
        String query = "SELECT * FROM buckets where user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            Bucket bucket = null;
            while (resultSet.next()) {
                bucket = getBucketFromResultSet(resultSet);
                bucket.setProducts(getAllProductsFromBucket(bucket));
            }
            LOGGER.info("The bucket by userId was retrieved successfully");
            return Optional.ofNullable(bucket);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve the bucket userId" + e);
        }
    }
}
