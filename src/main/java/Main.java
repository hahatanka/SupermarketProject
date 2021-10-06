import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    Scanner scanner = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);
    Supermarket ikea = new Supermarket();


    public static void main(String[] args) {

        Main main = new Main();
        main.Menu();
    }

    void Menu() {
        String userInput = "";
        do {
            System.out.println("\nWelcome to IKEA managing program");
            System.out.println("Please choose what you want to do: ");
            System.out.println("1. Register new User");
            System.out.println("2. Login");
            System.out.println("\nEnter QUIT to exit the program" +
                    "\n------------------------------------------");


            System.out.println("\nEnter the number here: ");
            userInput = scanner.nextLine().toUpperCase();

            switch (userInput) {
                case "1":
                    addNewUser();
                    break;
                case "2":
                    userLogin();
                    break;
                default:
                    break;
            }
        } while (!userInput.equalsIgnoreCase("Quit"));
        return;

    }

    void addNewUser() {
        System.out.println("ADD USER");
        System.out.println("Please enter name: ");
        String name = scanner.nextLine();

        System.out.println("Please enter email: ");
        String email = scanner.nextLine();

        System.out.println("Please enter password: ");
        String password = scanner.nextLine();

        System.out.println("Please enter type (a = sales representative, b = buyer, c = owner");
        String type = scanner.nextLine();

        if (type.equalsIgnoreCase("a")) {
            int balance = 0;

            User user = new User(name, email, password, type, balance);
            ikea.addUser(user);
            System.out.println("----------------------------------------------------------");
            System.out.println("New " + " " + user.getTypeA() + " " + user.getName() + " added successfully");
            System.out.println("----------------------------------------------------------");

        } else if (type.equalsIgnoreCase("b")) {
            System.out.println("Add money to your balance:");
            int balance = scannerInt.nextInt();
            User user = new User(name, email, password, type, balance);
            ikea.addUser(user);
            System.out.println("--------------------------------------------------------");
            System.out.println("New " + " " + user.getTypeB() + " " + user.getName() + " added successfully");
            System.out.println("---------------------------------------------------------");

        } else if (type.equalsIgnoreCase("c")) {
            int balance = 0;
            User user = new User(name, email, password, type, balance);
            ikea.addUser(user);
            System.out.println("---------------------------------------------------------");
            System.out.println("New " + " " + user.getTypeC() + " " + user.getName() + " added successfully");
            System.out.println("---------------------------------------------------------");

        } else {
            System.out.println("Invalid user type, please choose either A, B or C" +
                    "\n------------------------------------------");
        }
    }


    void userLogin() {
        User user;
        do {
            System.out.println("LOGIN MENU");
            System.out.println("Please enter your email: ");
            String inputEmail = scanner.nextLine();

            System.out.println("Please enter your password: ");
            String inputPassword = scanner.nextLine();

            user = ikea.findUser(inputEmail, inputPassword);

            if(user == null) {
                System.out.println("\n-------------------------------------" +
                        " \nInvalid email/password. Please try again" +
                        "\n----------------------------------------");}
        } while (user == null);


        System.out.println("-----------------------------------");
        System.out.println("Welcome to IKEA " + user.getName() + "!");
        System.out.println("-----------------------------------");

        if (user.getType().equalsIgnoreCase("a")) {
            userSalesRep();


        } else if (user.getType().equalsIgnoreCase("b")) {
            String userInput2;
            do {

                System.out.println("\nPlease choose what you want to do: ");
                System.out.println("1. Buy product");
                System.out.println("2. View available products");
                System.out.println("3. Show balance");
                System.out.println("Enter QUIT if you want to LOG OUT" +
                        "\n------------------------------------------");

               userInput2 = scanner.nextLine().toUpperCase();

                switch (userInput2) {
                    case "1":

                        System.out.println("Buy product");
                        System.out.println("Please enter the name of the product: ");
                        String name = scanner.nextLine();

                        Product product = ikea.findProduct(name);
                        if(product != null) {
                            System.out.println("Enter amount of " + product.getName() + " you want to buy: ");


                            int amountBought = scannerInt.nextInt();

                            int price = product.getSellPrice();
                            int moneyToPay = amountBought * price;

                            if ((product.getAmount() > amountBought) && user.getBalance() > (product.getSellPrice() * amountBought)) {

                                product.reduceAmount(amountBought);
                                ikea.updateProfit(moneyToPay);
                                user.updateBalance(moneyToPay);


                                System.out.println("You have bought " + amountBought + " pieces of " + name + ". " +
                                        "Total money paid " + moneyToPay +
                                        "\n------------------------------------------");

                            } else if (user.getBalance() < (product.getSellPrice() * amountBought)) {
                                System.out.println("!!! Not enough money to buy product" +
                                        "\n----------------------------");


                            } else if (product.getAmount() < amountBought) {
                                System.out.println("You cannot buy " + amountBought + " pieces of " + name +
                                        " since there are only " + product.getAmount() + " pieces left" +
                                        "\n------------------------------------------");
                            } else if (product.getAmount() == 0) {
                                System.out.println("!!! Sorry, the product is sold out! " +
                                        "\n----------------------------");
                            }
                        }else {
                            System.out.println("\n-------------------------------------" +
                                    "\nSorry, there is no " + name + " in IKEA " +
                                    "\n----------------------------------------");
                        }

                            break;
                    case "2":
                        viewAllProducts();
                        break;
                    case "3":
                        System.out.println("--------------------------------" +
                                "\nYour current Balance is: " + user.getBalance()+
                                "\n----------------------------");
                    default:
                        break;

                }
            } while (!userInput2.equalsIgnoreCase("quit"));
            return;


        } else if (user.getType().equalsIgnoreCase("c")) {
            userOwner();
        }
    }

    void userSalesRep(){
        String userInput;
        do {

            System.out.println("\nPlease choose what you want to do: ");
            System.out.println("1. Add product");
            System.out.println("2. Show amount of sold products");
            System.out.println("3. Show amount of products left");
            System.out.println("4. Show buying and selling price");
            System.out.println("5. Sales history");
            System.out.println("Enter QUIT if you want to LOG OUT" +
                    "\n----------------------------");

            userInput = scanner.nextLine().toUpperCase();


            switch (userInput) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    totalSold();
                    break;
                case "3":
                    productsLeft();
                    break;
                case "4":
                    viewBuyingSellingPrice();
                    break;
                case "5":
                    salesHistory();
                default:
                    break;
            }

        } while (!userInput.equalsIgnoreCase("quit"));
        return;
    }


    void userOwner(){
        String userInput3;
        do {

            System.out.println("\nPlease choose what you want to do: ");
            System.out.println("1. View sales history");
            System.out.println("2. Show balance");
            System.out.println("3. Show profit");
            System.out.println("4. View all users");
            System.out.println("Enter QUIT if you want to LOG OUT" +
                    "\n------------------------------------------");

            userInput3 = scanner.nextLine().toUpperCase();

            switch (userInput3) {
                case "1":
                    salesHistory();
                    break;
                case "2":
                    getBalanceForOwner();
                    break;
                case "3":
                    showProfit();
                    break;
                case "4":
                    showUsers();
                default:
                    break;
            }

        } while (!userInput3.equalsIgnoreCase("quit"));
        return;
    }

    void addProduct() {
        System.out.println("ADD PRODUCT");
        System.out.println("Please enter product name: ");
        String name = scanner.nextLine();

        System.out.println("Please enter buying price per piece: ");
        int buyingPrice = scannerInt.nextInt();

        System.out.println("Please enter selling price per piece: ");
        int sellingPrice = scannerInt.nextInt();

        System.out.println("Please enter available amount ");
        int amount = scannerInt.nextInt();

        Product product = new Product(name, buyingPrice, sellingPrice, amount);
        ikea.addProduct(product);
        ikea.updateLoss(amount, buyingPrice);

        System.out.println("-----------------------------------");
        System.out.println("Product " + product.getName() + " added successfully");
        System.out.println("-----------------------------------");
    }


    void totalSold() {

        System.out.println("Please enter the name of the product: ");
        String name = scanner.nextLine();
//create if statement

        Product product = ikea.findProduct(name);
        if (product.getName().equals(name)) {
// then...
            System.out.println("\n------------------------------------------" +
                    "\nTotal amount of " + name + " sold is " + product.getAmountProductSold()+
                    "\n------------------------------------------");
//else.. message
        } else {
            System.out.println("\n------------------------------------------" +
                    "No such product available, please try again" +
                    "\n------------------------------------------");
        }
    }

    void viewAllProducts() {

        System.out.println("All products available:");
        System.out.println("--------------------------------");
        for (Product product : ikea.getProducts()) {
            System.out.println("\nName: " + product + "\nPrice: " + product.getSellPrice() + "\n--------------------------");
        }

    }

    void productsLeft() {
        System.out.println("Please enter the name of the product: ");
        String name = scanner.nextLine();
        Product product = ikea.findProduct(name);
        if (product.getName().equals(name)) {
            int initialAmount = product.getAmount() + product.getAmountProductSold();
            int amountLeft = product.getAmount();

            System.out.println("\n------------------------------------------" +
                    "\nInitial amount of " + name + " was " + initialAmount + ". " +
                    "\nAmount of product sold is " + product.getAmountProductSold() +
                    "\nAmount of product left: " + amountLeft+
                    "\n------------------------------------------");
        } else {
            System.out.println("\n------------------------------------------" +
                    "No such product available, please try again" +
                    "\n------------------------------------------");
        }
    }

    void viewBuyingSellingPrice() {
        System.out.println("Please enter the name of the product: ");
        String name = scanner.nextLine();
        Product product = ikea.findProduct(name);
        if (product.getName().equals(name)) {
            System.out.println("\n------------------------------------------" +
                    "\nProduct: " + name + "\nBuying Price: " + product.getBuyPrice() +
                    "\nSelling Price: " + product.getSellPrice()+
                    "\n------------------------------------------");
        } else {
            System.out.println("No such product available, please try again");
        }
    }

    void salesHistory() {
        System.out.println("--------------------------------");
        System.out.println("SALES HISTORY: "+
                "\n--------------------------------");
        for (Product product : ikea.getProducts()) {
            System.out.println("Name: " + product + "\nPrice: " + product.getSellPrice() + "\nAmount Sold: " + product.getAmountProductSold() + "\n--------------------------");
        }

    }

    void showProfit(){
        System.out.println("--------------------------------");
        System.out.println("Current Profit " + ikea.getProfit()+
                "\n------------------------------------------");
    }

    void showUsers(){

        System.out.println("--------------------------------");
        System.out.println("ALL USERS REGISTERED: " + "\n" + ikea.getUsers());
    }

    void getBalanceForOwner(){
        System.out.println("\nBALANCE"+
                "\n---------------------------------" +
                "\nTotal Profit: " + ikea.getProfit()+
                "\nTotal Loss: " + ikea.getLoss()+
                "\nTotal Earned: " + ikea.getBalanceForOwner()+
                "\n---------------------------------");
    }
}





