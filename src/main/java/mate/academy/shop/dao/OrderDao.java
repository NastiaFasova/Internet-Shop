package mate.academy.shop.dao;

import java.util.List;
import mate.academy.shop.model.Order;

public interface OrderDao extends GenericDao<Order, Long> {
    List<Order> getByUserId(Long userId);
}
