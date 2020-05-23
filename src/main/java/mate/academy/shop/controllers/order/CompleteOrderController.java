package mate.academy.shop.controllers.order;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.service.BucketService;
import mate.academy.shop.service.OrderService;
import mate.academy.shop.service.UserService;

public class CompleteOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.shop");
    private static final String USER_ID = "user_id";
    private final OrderService orderService
            = (OrderService) INJECTOR.getInstance(OrderService.class);
    private final UserService userService
            = (UserService) INJECTOR.getInstance(UserService.class);
    private final BucketService bucketService
            = (BucketService) INJECTOR.getInstance(BucketService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        orderService.completeOrder(bucketService.getByUserId(userId).getProducts(),
                userService.get(userId));
        bucketService.clear(bucketService.getByUserId(userId));
        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}
