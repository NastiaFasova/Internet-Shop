package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.ConnectionUtil;
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoJdbcImpl.class);

    @Override
    public Optional<User> findByLogin(String login) {
        String query = "SELECT * FROM users WHERE login = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't find the user by the login" + e);
        }
        return Optional.empty();
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users(name, login, password) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement
                    = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            addRoles(user);
            LOGGER.info("The user is created");
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add the user" + e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't find the user by the ID" + e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                users.add(user);
            }
            LOGGER.info("Information about all users is got");
            return users;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all the users" + e);
        }
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET name = ?, login = ?, password = ? WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
            deleteRolesOfUsers(user.getId());
            addRoles(user);
            LOGGER.info("Information about the user id updated");
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update the user" + e);
        }
    }

    @Override
    public boolean delete(Long id) {
        deleteRolesOfUsers(id);
        String query = "DELETE FROM users WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            LOGGER.info("The user is successfully deleted");
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete the user" + e);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        long userId = resultSet.getLong("user_id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        User user = new User(name, login, password);
        user.setId(userId);
        user.setRoles(getRoles(userId));
        LOGGER.info("The object of user is created");
        return user;
    }

    private void addRoles(User user) throws SQLException {
        String query = "INSERT INTO users_roles(user_fk, role_fk) VALUES(?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Role role : user.getRoles()) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, user.getId());
                if (role.getId() == null) {
                    role.setId(getRoleId(role.getRoleName(), connection));
                }
                statement.setLong(2, role.getId());
                statement.executeUpdate();
            }
        }
    }

    private Set<Role> getRoles(Long userId) {
        Set<Role> roles = new HashSet<>();
        String query = "SELECT roles.role_name FROM users_roles"
                + " JOIN roles ON users_roles.role_fk = roles.role_id "
                + "WHERE users_roles.user_fk = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roles.add(Role.of(resultSet.getString("role_name")));
            }
            LOGGER.info("The roles of user are successfully got");
            return roles;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get the roles" + e);
        }
    }

    private Long getRoleId(Role.RoleName roleName, Connection connection) throws SQLException {
        String query = "SELECT role_id FROM roles WHERE role_name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, roleName.toString());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getLong("role_id");
    }

    private void deleteRolesOfUsers(Long userId) {
        String query = "DELETE FROM users_roles WHERE user_fk = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DataProcessingException("Can't delete user from users_roles");
        }
    }
}
