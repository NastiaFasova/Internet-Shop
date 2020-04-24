package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.model.Product;

public interface ProductService {

    Product create(Product item);

    Product get(Long id);

    List<Product> getAll();

    Product update(Product item);

    boolean delete(Long id);

    boolean delete(Product item);
}
