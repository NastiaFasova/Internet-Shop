package mate.academy.internetshop.controllers.bucket;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ProductService;

public class DeleteProductFromBucketController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private static final Long USER_ID = 1L;
    private ProductService productService
            = (ProductService) INJECTOR.getInstance(ProductService.class);
    private BucketService bucketService
            = (BucketService) INJECTOR.getInstance(BucketService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String productId = req.getParameter("id");
        Long id = Long.valueOf(productId);
        bucketService.deleteProduct(bucketService.getByUserId(USER_ID), productService.get(id));
        req.getRequestDispatcher("/bucket/show").forward(req, resp);
    }
}
