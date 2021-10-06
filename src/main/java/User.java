public class User {
    private String name;
    private String email;
    private String password;
    private String type;
    private int balance;


    User(String name, String email, String password, String type, int balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.balance = balance;

    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public String getTypeA() {
        return "sales representative";

    }

    public String getTypeB() {
        return "buyer";
    }

    public String getTypeC() {
        return "owner";
    }

    public String getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    public int getBalance() {
        return balance;
    }

    public void updateBalance(int moneyToPay) {
        this.balance -= moneyToPay;


    }

    public String toString() {
        return "\nName: " + name +
                "\nType: " + type +
                "\nEmail: " + email +
                "\n--------------------------------";
    }
}
