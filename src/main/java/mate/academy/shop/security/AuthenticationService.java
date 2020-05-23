package mate.academy.shop.security;

import mate.academy.shop.exceptions.AuthenticationException;
import mate.academy.shop.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;
}
