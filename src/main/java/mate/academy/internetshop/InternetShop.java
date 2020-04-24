package mate.academy.internetshop;

import java.util.List;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.UserService;

public class InternetShop {

    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        final ProductService itemService
                = (ProductService) injector.getInstance(ProductService.class);
        final OrderService orderService
                = (OrderService) injector.getInstance(OrderService.class);
        final UserService userService
                = (UserService) injector.getInstance(UserService.class);
        final BucketService bucketService
                = (BucketService) injector.getInstance(BucketService.class);

        initializeItem(itemService);
        initializeUser(userService);

        List<Product> items = itemService.getAll();
        List<User> users = userService.getAll();
        System.out.println("After initializing: ");
        printData(items);
        printData(users);

        itemService.delete(items.get(1));
        userService.delete(1L);
        System.out.println("\nAfter deleting: ");
        printData(items);
        printData(users);

        System.out.println("\nAfter creating a new item: ");
        Product item = new Product("Nokia", 130.98);
        User user = new User("Igor", "igor456", "8790");
        itemService.create(item);
        userService.create(user);
        printData(items);
        printData(users);

        item.setName("Samsung");
        item.setPrice(200.80);
        user.setName("John");
        itemService.update(item);
        userService.update(user);
        System.out.println("\nAfter updating: ");
        printData(items);
        printData(users);

        System.out.println("\nUsers orders: " + orderService.getUserOrders(user));

        Bucket bucket = new Bucket(user);
        Product product = new Product("Sony", 100.90);

        bucketService.addProduct(bucket, product);
        System.out.println("\nGet by UserId: " + bucketService.getByUserId(user.getId()));

        System.out.println("\nAfter removing the product(Sony)"
                + bucketService.getAllProducts(bucket));

        Order order = new Order(items, user);
        orderService.delete(order.getId());
        System.out.println("\nAfter deleting the order: " + orderService.getUserOrders(user));
    }

    private static void printData(List<?> items) {
        items.forEach(System.out::println);
    }

    private static void initializeItem(ProductService itemService) {
        Product item1 = new Product("IPhone", 450.50);
        Product item2 = new Product("Samsung", 250.60);
        Product item3 = new Product("Meizu", 190.50);
        itemService.create(item1);
        itemService.create(item2);
        itemService.create(item3);
    }

    private static void initializeUser(UserService userService) {
        User user1 = new User("Oleg", "oleh@788", "00000");
        User user2 = new User("Dmytro", "dmytro888", "9999");
        User user3 = new User("Vasia", "vasia322", "789");
        userService.create(user1);
        userService.create(user2);
        userService.create(user3);
    }
}
