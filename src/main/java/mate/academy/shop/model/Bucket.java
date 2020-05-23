package mate.academy.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bucket {
    private Long id;
    private List<Product> products;
    private Long userId;

    public Bucket(Long userId) {
        products = new ArrayList<>();
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Bucket{"
                + "id=" + id + ", products=" + products
                + ", userId=" + userId + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bucket bucket = (Bucket) o;
        return Objects.equals(id, bucket.id)
                && Objects.equals(products, bucket.products)
                && Objects.equals(userId, bucket.userId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (products != null ? products.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
