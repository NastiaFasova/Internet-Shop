package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Optional<Bucket> getByUserId(Long userId) {
        return Storage.buckets
                .stream()
                .filter(b -> b.getUser().getId().equals(userId))
                .findFirst();
    }

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
                .filter(x -> bucket.getId().equals(Storage.buckets.get(x).getId()))
                .forEach(i -> Storage.buckets.set(i, bucket));
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
