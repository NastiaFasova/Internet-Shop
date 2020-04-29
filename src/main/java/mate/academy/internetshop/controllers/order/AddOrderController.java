package mate.academy.internetshop.controllers.order;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private static final Long USER_ID = 1L;

    private OrderService orderService = (OrderService) INJECTOR.getInstance(OrderService.class);

    private ProductService productService
            = (ProductService) INJECTOR.getInstance(ProductService.class);
    private UserService userService
            = (UserService) INJECTOR.getInstance(UserService.class);
    private BucketService bucketService
            = (BucketService) INJECTOR.getInstance(BucketService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Bucket bucket = bucketService.getByUserId(USER_ID);
        Order order = orderService.completeOrder(bucket.getProducts(),
                bucket.getUser());
        resp.sendRedirect(req.getContextPath() + "/order/all");
    }
}
