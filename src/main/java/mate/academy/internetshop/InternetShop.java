package mate.academy.internetshop;

import java.util.List;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;

public class InternetShop {

    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        ProductService itemService = (ProductService) injector.getInstance(ProductService.class);

        initializeItem(itemService);

        List<Product> items = itemService.getAll();
        System.out.println("After initializing: ");
        printData(items);

        itemService.delete(items.get(1));
        System.out.println("After deleting: ");
        printData(items);

        System.out.println("After creating a new item: ");
        Product item = new Product("Nokia", 130.98);
        itemService.create(item);
        printData(items);

        item.setName("Samsung");
        item.setPrice(200.80);
        itemService.update(item);
        System.out.println("After updating: ");
        printData(items);
    }

    private static void printData(List<Product> items) {
        items.forEach(System.out::println);
    }

    private static void initializeItem(ProductService itemService) {
        Product item1 = new Product("IPhone", 450.50);
        Product item2 = new Product("Samsung", 250.60);
        Product item3 = new Product("Meizu", 190.50);
        itemService.create(item1);
        itemService.create(item2);
        itemService.create(item3);
    }
}
