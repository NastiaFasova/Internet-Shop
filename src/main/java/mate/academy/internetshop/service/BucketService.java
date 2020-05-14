package mate.academy.internetshop.service;

import java.sql.SQLException;
import java.util.List;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Product;

public interface BucketService extends GenericService<Bucket, Long> {

    Bucket addProduct(Bucket bucket, Product product) throws SQLException;

    boolean deleteProduct(Bucket bucket, Product product) throws SQLException;

    void clear(Bucket bucket);

    Bucket getByUserId(Long userId);

    List<Product> getAllProducts(Bucket bucket);
}
