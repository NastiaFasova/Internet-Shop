package mate.academy.shop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.shop.dao.OrderDao;
import mate.academy.shop.lib.Inject;
import mate.academy.shop.lib.Service;
import mate.academy.shop.model.Order;
import mate.academy.shop.model.Product;
import mate.academy.shop.model.User;
import mate.academy.shop.service.BucketService;
import mate.academy.shop.service.OrderService;

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
        Order newOrder = new Order(orderProducts, user.getId());
        return orderDao.create(newOrder);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getAll()
                .stream()
                .filter(u -> u.getUserId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Order create(Order element) {
        return orderDao.create(element);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order update(Order element) {
        return orderDao.update(element);
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
