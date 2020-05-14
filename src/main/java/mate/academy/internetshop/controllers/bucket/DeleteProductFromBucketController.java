package mate.academy.internetshop.controllers.bucket;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ProductService;

public class DeleteProductFromBucketController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private static final String USER_ID = "user_id";
    private ProductService productService
            = (ProductService) INJECTOR.getInstance(ProductService.class);
    private BucketService bucketService
            = (BucketService) INJECTOR.getInstance(BucketService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        String productId = req.getParameter("id");
        Long id = Long.valueOf(productId);
        try {
            bucketService.deleteProduct(bucketService.getByUserId(userId), productService.get(id));
        } catch (SQLException e) {
            throw new DataProcessingException("The product can't be deleted");
        }
        req.getRequestDispatcher("/bucket/show").forward(req, resp);
    }
}
