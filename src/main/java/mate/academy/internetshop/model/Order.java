package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private List<Product> items = new ArrayList<>();
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
