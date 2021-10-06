public class Product {

    private String name;
    private int buyPrice;
    private int sellPrice;
    private int amount;
    private int amountSold;



    //Product constructor


    Product(String name, int buyPrice, int sellPrice, int amount) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.amount = amount;


    }


    public String getName() {
        return name;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getAmount() {
        return amount;
    }

    //reduce amount after buying
    public void reduceAmount(int amountBought) {
        this.amount -= amountBought;
        updateAmountSold(amountBought);

    }



    public String toString() {
        return name;
    }
    public int getAmountProductSold(){
        return amountSold;
    }

    public Product updateAmountSold(int productsSold) {
        this.amountSold += productsSold;
        return null;
    }


}
