package mate.academy.internetshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderDao orderDao;

    @Inject
    private BucketService bucketService;

    @Override
    public Order completeOrder(List<Product> products, User user) {
        List<Product> orderProducts = new ArrayList<>(products);
        bucketService.clear(bucketService.getByUserId(user.getId()));
        Order order = new Order(orderProducts, user);
        return orderDao.create(order);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getAll()
                .stream()
                .filter(o -> o.getUser().equals(user))
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
