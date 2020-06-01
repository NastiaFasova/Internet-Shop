package mate.academy.shop.service;

import mate.academy.shop.model.Bucket;
import mate.academy.shop.model.Product;

public interface BucketService extends GenericService<Bucket, Long> {

    Bucket addProduct(Bucket bucket, Product product);

    boolean deleteProduct(Bucket bucket, Product product);

    void clear(Bucket bucket);

    Bucket getByUserId(Long userId);
}
