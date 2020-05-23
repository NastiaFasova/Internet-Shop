package mate.academy.shop.dao;

import java.util.Optional;
import mate.academy.shop.model.User;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> findByLogin(String login);
}
