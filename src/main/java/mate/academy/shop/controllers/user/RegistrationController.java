package mate.academy.shop.controllers.user;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.Role;
import mate.academy.shop.model.User;
import mate.academy.shop.service.UserService;
import org.apache.log4j.Logger;

public class RegistrationController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.shop");
    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);
    private final UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/users/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("psw");
        String repeatPassword = req.getParameter("psw-repeat");

        if (password.equals(repeatPassword)) {
            User user = new User(name, login, password);
            user.setRoles(Set.of(Role.of("USER")));
            userService.create(user);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            LOGGER.warn("The user's password and repeat-password are not the same");
            req.setAttribute("message", "Your password and repeat password aren't the same");
            req.getRequestDispatcher("/WEB-INF/views/users/register.jsp").forward(req, resp);
        }
    }
}
