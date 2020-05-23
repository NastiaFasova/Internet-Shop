package mate.academy.shop.web.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.Role;
import mate.academy.shop.model.User;
import mate.academy.shop.service.UserService;
import org.apache.log4j.Logger;

public class AuthorizationFilter implements Filter {
    private static final String USER_ID = "user_id";
    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.shop");
    private final Map<String, Set<Role.RoleName>> protectedUrls = new HashMap<>();
    private final UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    public void init(FilterConfig filterConfig) {
        protectedUrls.put("/users", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/order/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/bucket/show", Set.of(Role.RoleName.USER));
        protectedUrls.put("/product/buy", Set.of(Role.RoleName.USER));
        protectedUrls.put("/orders", Set.of(Role.RoleName.USER));
        protectedUrls.put("/bucket/product/remove", Set.of(Role.RoleName.USER));
        protectedUrls.put("/order/add", Set.of(Role.RoleName.USER));
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
        User user = userService.get(userId);
        if (isAuthorized(user, protectedUrls.get(requestUrl))) {
            filterChain.doFilter(req, resp);
        } else {
            LOGGER.warn("User tried to visit the forbidden page");
            req.getRequestDispatcher("/WEB-INF/views/access_denied.jsp").forward(req, resp);
        }

    }

    public static boolean isAuthorized(User user, Set<Role.RoleName> roleNames) {
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
