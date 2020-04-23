package mate.academy.internetshop.service.impl;

import java.util.List;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ProductDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private BucketDao bucketDao;

    @Inject
    private ProductDao productDao;

    @Override
    public Bucket addProduct(Bucket bucket, Product product) {
        Bucket newBucket = bucketDao.create(bucket);
        newBucket.getProducts().add(product);
        return bucketDao.update(newBucket);
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
        bucketDao.update(bucket);
    }

    @Override
    public Bucket getByUserId(Long userId) {
        return bucketDao.getByUserId(userId).get();
    }

    @Override
    public List<Product> getAllProducts(Bucket bucket) {
        return bucket.getProducts();
    }
}
