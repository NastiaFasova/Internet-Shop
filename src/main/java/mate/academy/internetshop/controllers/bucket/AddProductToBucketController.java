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

    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private static final Long USER_ID = 1L;

    private BucketService bucketService = (BucketService) INJECTOR.getInstance(BucketService.class);

    private ProductService productService
            = (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String productId = req.getParameter("id");
        Bucket bucket = bucketService.getByUserId(USER_ID);
        bucketService.addProduct(bucket, productService.get(Long.valueOf(productId)));
        resp.sendRedirect(req.getContextPath() + "/bucket/show");
    }
}
