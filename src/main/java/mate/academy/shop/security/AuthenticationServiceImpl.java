package mate.academy.shop.security;

import mate.academy.shop.exceptions.AuthenticationException;
import mate.academy.shop.lib.Inject;
import mate.academy.shop.lib.Service;
import mate.academy.shop.model.User;
import mate.academy.shop.service.UserService;
import mate.academy.shop.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User userFromDB = userService.findByLogin(login).orElseThrow(
                () -> new AuthenticationException("Wrong login or password"));
        if (userFromDB.getPassword()
                .equals(HashUtil.hashPassword(password, userFromDB.getSalt()))) {
            return userFromDB;
        }
        throw new AuthenticationException("Wrong login or password");
    }
}
