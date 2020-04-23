package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(List<Product> products, User user) {
        return orderDao.create(new Order(products,user));
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return Storage.orders
                .stream()
                .filter(u -> u.getUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
