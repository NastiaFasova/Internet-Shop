package mate.academy.shop.service;

import java.sql.SQLException;
import java.util.List;

public interface GenericService<T, K> {

    T create(T element);

    T get(K id);

    List<T> getAll();

    T update(T element) throws SQLException;

    boolean delete(K id);
}
