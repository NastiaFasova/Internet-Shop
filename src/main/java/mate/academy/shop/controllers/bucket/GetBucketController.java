package mate.academy.shop.controllers.bucket;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.Product;
import mate.academy.shop.service.BucketService;

public class GetBucketController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.shop");
    private final BucketService bucketService
            = (BucketService) INJECTOR.getInstance(BucketService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        List<Product> products = bucketService.getAllProducts(bucketService.getByUserId(userId));
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/bucket/show.jsp").forward(req, resp);
    }
}
