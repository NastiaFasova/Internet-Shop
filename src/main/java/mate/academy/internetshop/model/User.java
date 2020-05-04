package mate.academy.internetshop.model;

import java.util.List;

public class User {
    private Long id;
    private String name;
    private String login;
    private String password;
    private List<Role> roles;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(String name, String login) {
        this.name = name;
        this.login = login;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{ name = " + name + '\''
                + ", login='" + login + '\''
                + ", password='" + password
                + '\'' + '}';
    }
}
