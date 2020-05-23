package mate.academy.shop.controllers.bucket;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.service.BucketService;
import mate.academy.shop.service.ProductService;

public class DeleteProductFromBucketController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.shop");
    private static final String USER_ID = "user_id";
    private final ProductService productService
            = (ProductService) INJECTOR.getInstance(ProductService.class);
    private final BucketService bucketService
            = (BucketService) INJECTOR.getInstance(BucketService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        String productId = req.getParameter("id");
        Long id = Long.valueOf(productId);
        bucketService.deleteProduct(bucketService.getByUserId(userId), productService.get(id));
        req.getRequestDispatcher("/bucket/show").forward(req, resp);
    }
}
