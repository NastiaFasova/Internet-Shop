package mate.academy.internetshop;

import java.math.BigDecimal;
import java.sql.SQLException;

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

    public static void main(String[] args) throws SQLException {
        final ProductService productService
                = (ProductService) injector.getInstance(ProductService.class);
        final OrderService orderService
                = (OrderService) injector.getInstance(OrderService.class);
        final UserService userService
                = (UserService) injector.getInstance(UserService.class);
        final BucketService bucketService
                = (BucketService) injector.getInstance(BucketService.class);

        System.out.println("\nTesting product service: ");
        testProduct(productService);
        System.out.println("\nTesting user service: ");
        testUser(userService);
        System.out.println("\nTesting bucket service: ");
        testBucket(userService, bucketService);
        System.out.println("\nTesting order service: ");
        testOrder(productService, orderService, userService, bucketService);
    }

    private static void testOrder(ProductService productService, OrderService orderService,
                                  UserService userService, BucketService bucketService) throws SQLException {
        User user = new User("Arthur", "Arcana Lord", "123123123");
        userService.create(user);
        Product phone = new Product("NOKIA789", new BigDecimal(250.00));
        Product panasonic = new Product("Panasonic", new BigDecimal(150.000));
        Product iphone = new Product("IPhoneX", new BigDecimal(100.000));
        productService.create(phone);
        productService.create(panasonic);
        productService.create(iphone);
        Bucket bucket = bucketService.getByUserId(user.getId());
        bucketService.addProduct(bucket, phone);
        bucketService.addProduct(bucket, panasonic);
        bucketService.addProduct(bucket, iphone);
        Order order = orderService.completeOrder(bucketService.getAllProducts(bucket), user);
        System.out.println("Get by orderId: " + orderService.get(order.getId()));
        System.out.println("Get by user Id" + orderService.getUserOrders(user));
    }

    private static void testBucket(UserService userService, BucketService bucketService) throws SQLException {
        User user = new User("Artem", "artem890", "1231123");
        userService.create(user);
        Product phone = new Product("NOKIA789", new BigDecimal(250.00));
        Product panasonic = new Product("Panasonic", new BigDecimal(1500.00));
        Product iphone = new Product("IPhoneX", new BigDecimal(1000.00));
        Bucket bucket = bucketService.getByUserId(user.getId());
        bucketService.addProduct(bucket, phone);
        bucketService.addProduct(bucket, panasonic);
        bucketService.addProduct(bucket, iphone);
        System.out.println("After adding 3 items: " + bucketService.getAllProducts(bucket));
        bucketService.deleteProduct(bucket, phone);
        System.out.println("After deleting an item: " + bucketService.getAllProducts(bucket));
        userService.delete(user.getId());
    }

    private static void testProduct(ProductService productService) {
        Product item1 = new Product("IPhone", new BigDecimal(450.50));
        Product item2 = new Product("Samsung", new BigDecimal(250.60));
        Product item3 = new Product("Meizu", new BigDecimal(190.50));
        productService.create(item1);
        productService.create(item2);
        productService.create(item3);
        System.out.println(productService.getAll());
        System.out.println(productService.get(item1.getId()));
        productService.delete(item1.getId());
        System.out.println(productService.getAll());
    }

    private static void testUser(UserService userService) {
        User user1 = new User("Oleg", "oleh@788", "00000");
        User user2 = new User("Dmytro", "dmytro888", "9999");
        User user3 = new User("Vasia", "vasia322", "789");
        userService.create(user1);
        userService.create(user2);
        userService.create(user3);
        System.out.println(userService.getAll());
        System.out.println(userService.get(user1.getId()));
        userService.delete(user1.getId());
        System.out.println(userService.getAll());
        System.out.println("*****************");
    }
}
