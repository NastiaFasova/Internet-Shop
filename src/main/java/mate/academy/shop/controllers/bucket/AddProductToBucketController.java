package mate.academy.shop.controllers.bucket;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.Bucket;
import mate.academy.shop.service.BucketService;
import mate.academy.shop.service.ProductService;

public class AddProductToBucketController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.shop");
    private final BucketService bucketService
            = (BucketService) INJECTOR.getInstance(BucketService.class);
    private final ProductService productService
            = (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = (Long)req.getSession().getAttribute(USER_ID);
        String productId = req.getParameter("id");
        Bucket bucket = bucketService.getByUserId(userId);
        bucketService.addProduct(bucket, productService.get(Long.valueOf(productId)));
        resp.sendRedirect(req.getContextPath() + "/bucket/show");
    }
}
