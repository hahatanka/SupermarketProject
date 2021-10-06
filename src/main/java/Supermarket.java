import java.util.ArrayList;
import java.util.Objects;

public class Supermarket extends User {

    public ArrayList<Product> products;
    public ArrayList<User> users;
    private static int profit;
    private static int loss;


    Supermarket() {
        products = new ArrayList<>();
        users = new ArrayList<>();
        profit = 0;


    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public User findUser(String inputEmail, String inputPassword) {

        for (User user : users)
            if ((Objects.equals(inputEmail, user.getEmail())) && ((Objects.equals(inputPassword, user.getPassword())))) {
                return user;
            }
        return null;
    }


    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Product findProduct(String name) {
        for (Product product : products)
            if (Objects.equals(name, product.getName())) {
                return product;
            }
        return null;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }


    public int getProfit() {
        return profit;
    }

    public Product updateProfit(int moneyPaidByBuyers) {
        profit += moneyPaidByBuyers;
        return null;
    }


    public ArrayList<User> getUsers() {
        return users;
    }

    public int getLoss(){
        return loss;
    }

    public Product updateLoss(int amount, int price) {
        this.loss = amount * price;
        return null;
    }

    public int getBalanceForOwner(){
        return profit - loss;
    }
}







