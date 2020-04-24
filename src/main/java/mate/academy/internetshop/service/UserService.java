package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.model.User;

public interface UserService {

    User create(User user);

    User get(Long id);

    List<User> getAll();

    User update(User user);

    boolean delete(Long id);
}