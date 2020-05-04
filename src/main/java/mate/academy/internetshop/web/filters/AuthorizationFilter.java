package mate.academy.internetshop.web.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class AuthorizationFilter implements Filter {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private Map<String, List<Role.RoleName>> protectedUrls = new HashMap<>();
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/users", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/bucket/show", List.of(Role.RoleName.USER));
        protectedUrls.put("/product/buy", List.of(Role.RoleName.USER));
        protectedUrls.put("/bucket/product/remove", List.of(Role.RoleName.USER));
        protectedUrls.put("/order/add", List.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String requestUrl = req.getServletPath();
        if (protectedUrls.get(requestUrl) == null) {
            filterChain.doFilter(req, resp);
            return;
        }
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId == null || userService.get(userId) == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        User user = userService.get(userId);
        if (isAuthorized(user, protectedUrls.get(requestUrl))) {
            filterChain.doFilter(req, resp);
            return;
        } else {
            req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
            return;
        }

    }

    public static boolean isAuthorized(User user, List<Role.RoleName> roleNames) {
        for (Role.RoleName roleName : roleNames) {
            for (Role userRole : user.getRoles()) {
                if (roleName.equals(userRole.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
