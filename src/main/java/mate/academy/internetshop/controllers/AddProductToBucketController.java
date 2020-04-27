package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ProductService;

public class AddProductToBucketController extends HttpServlet {

    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");
    private static final Long USER_ID = 1L;

    @Inject
    private BucketService bucketService = (BucketService) injector.getInstance(BucketService.class);

    @Inject
    private ProductService productService
            = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String productId = req.getParameter("id");
        Bucket bucket = bucketService.getByUserId(USER_ID);
        bucketService.addProduct(bucket, productService.get(Long.valueOf(productId)));
        resp.sendRedirect(req.getContextPath() + "/buckets/all");
    }
}
