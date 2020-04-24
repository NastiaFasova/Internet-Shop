package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.service.UserService;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Inject
    private UserService userService;

    @Override
    public Bucket create(Bucket bucket) {
        Storage.addBucket(bucket);
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long id) {
        return Storage.buckets
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Bucket> getAll() {
        return Storage.buckets;
    }

    @Override
    public Bucket update(Bucket bucket) {
        IntStream.range(0, Storage.buckets.size())
                .filter(ind -> Storage.buckets.get(ind).getId().equals(bucket.getId()))
                .forEach(ind -> Storage.buckets.set(ind, bucket));
        return bucket;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.buckets.removeIf(bucket -> bucket.getId().equals(id));
    }

    @Override
    public boolean delete(Bucket bucket) {
        return Storage.buckets.remove(bucket);
    }
}