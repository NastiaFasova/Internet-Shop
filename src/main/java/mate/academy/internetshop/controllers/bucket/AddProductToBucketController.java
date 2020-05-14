package mate.academy.internetshop.controllers.bucket;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ProductService;

public class AddProductToBucketController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private BucketService bucketService = (BucketService) INJECTOR.getInstance(BucketService.class);
    private ProductService productService
            = (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long)req.getSession().getAttribute(USER_ID);
        String productId = req.getParameter("id");
        Bucket bucket = bucketService.getByUserId(userId);
        bucketService.addProduct(bucket, productService.get(Long.valueOf(productId)));
        resp.sendRedirect(req.getContextPath() + "/bucket/show");
    }
}
