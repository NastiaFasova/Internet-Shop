package mate.academy.shop.service;

import java.util.List;
import mate.academy.shop.model.Order;
import mate.academy.shop.model.Product;
import mate.academy.shop.model.User;

public interface OrderService extends GenericService<Order, Long> {

    Order completeOrder(List<Product> products, User user);

    List<Order> getUserOrders(User user);
}
