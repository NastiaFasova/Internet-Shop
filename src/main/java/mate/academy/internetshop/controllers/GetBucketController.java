package mate.academy.internetshop.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.BucketService;

public class GetBucketController extends HttpServlet {
    private static final Long USER_ID = 1L;

    private static final Injector injector = Injector.getInstance("mate.academy.internetshop");

    @Inject
    private BucketService bucketService
            = (BucketService) injector.getInstance(BucketService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> products = bucketService.getAllProducts(bucketService.getByUserId(USER_ID));
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/buckets/all.jsp").forward(req, resp);
    }
}
