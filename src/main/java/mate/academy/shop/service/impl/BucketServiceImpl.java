package mate.academy.shop.service.impl;

import java.sql.SQLException;
import java.util.List;
import mate.academy.shop.dao.BucketDao;
import mate.academy.shop.lib.Inject;
import mate.academy.shop.lib.Service;
import mate.academy.shop.model.Bucket;
import mate.academy.shop.model.Product;
import mate.academy.shop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private BucketDao bucketDao;

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
    public Bucket update(Bucket element) {
        return bucketDao.update(element);
    }

    @Override
    public boolean delete(Long id) {
        return bucketDao.delete(id);
    }
}
