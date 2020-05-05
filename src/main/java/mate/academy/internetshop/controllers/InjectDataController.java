package mate.academy.internetshop.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.UserService;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private ProductService productService
            = (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User steve = new User("Steve", "steve12", "12345");
        steve.setRoles(Set.of(Role.of("USER")));
        User john = new User("John", "john789", "098tdy");
        john.setRoles(Set.of(Role.of("USER")));
        User chris = new User("Chris", "chris789", "648fgj");
        chris.setRoles(Set.of(Role.of("USER")));
        User admin = new User("admin", "admin", "0909");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        userService.create(steve);
        userService.create(john);
        userService.create(chris);
        userService.create(admin);

        productService.create(new Product("Milk", new BigDecimal(15)));
        productService.create(new Product("Sugar", new BigDecimal(20)));
        productService.create(new Product("Butter", new BigDecimal(17)));
        productService.create(new Product("Bread", new BigDecimal(15)));
        productService.create(new Product("Bounty", new BigDecimal(22)));

        req.getRequestDispatcher("/WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
