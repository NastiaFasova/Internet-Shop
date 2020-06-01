package mate.academy.shop.dao;

import java.util.Optional;
import mate.academy.shop.model.Bucket;

public interface BucketDao extends GenericDao<Bucket, Long> {
    Optional<Bucket> getByUserId(Long userId);
}
