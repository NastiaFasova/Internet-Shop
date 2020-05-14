package mate.academy.internetshop.service.impl;

import java.sql.SQLException;
import java.util.List;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.UserService;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private BucketDao bucketDao;

    @Inject
    private UserService userService;

    @Override
    public Bucket addProduct(Bucket bucket, Product product) {
        bucket.getProducts().add(product);
        bucketDao.update(bucket);
        return bucket;
    }

    @Override
    public boolean deleteProduct(Bucket bucket, Product product) {
        if (bucket.getProducts().remove(product)) {
            bucketDao.update(bucket);
            return true;
        }
        return false;
    }

    @Override
    public void clear(Bucket bucket) {
        bucket.getProducts().clear();
        bucketDao.delete(bucket.getId());
    }

    @Override
    public Bucket getByUserId(Long userId) {
        return bucketDao.getAll().stream()
                .filter(bucket -> bucket.getUserId().equals(userId))
                .findFirst()
                .orElseGet(() -> bucketDao.create(
                        new Bucket(userId)));
    }

    @Override
    public List<Product> getAllProducts(Bucket bucket) {
        return bucket.getProducts();
    }

    @Override
    public Bucket create(Bucket element) {
        return bucketDao.create(element);
    }

    @Override
    public Bucket get(Long id) {
        return bucketDao.get(id).get();
    }

    @Override
    public List<Bucket> getAll() {
        return bucketDao.getAll();
    }

    @Override
    public Bucket update(Bucket element) throws SQLException {
        return bucketDao.update(element);
    }

    @Override
    public boolean delete(Long id) {
        return bucketDao.delete(id);
    }
}
