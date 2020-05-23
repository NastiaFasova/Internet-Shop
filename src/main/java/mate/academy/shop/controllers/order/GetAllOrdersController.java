package mate.academy.shop.controllers.order;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.Order;
import mate.academy.shop.service.OrderService;
import mate.academy.shop.service.UserService;

public class GetAllOrdersController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.shop");
    private final OrderService orderService
            = (OrderService) INJECTOR.getInstance(OrderService.class);
    private final UserService userService
            = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        List<Order> orders = orderService.getUserOrders(userService.get(userId));
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/views/order/all.jsp").forward(req, resp);
    }
}
