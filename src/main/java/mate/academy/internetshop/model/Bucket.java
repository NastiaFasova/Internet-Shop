package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long id;
    private List<Product> products;
    private User user;

    public Bucket(User user) {
        products = new ArrayList<>();
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Bucket{"
                + "id=" + id + ", products=" + products
                + ", user=" + user + '}';
    }
}
