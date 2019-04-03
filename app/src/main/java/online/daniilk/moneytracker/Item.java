package online.daniilk.moneytracker;

public class Item {

    private String title; // private - защещённость
    private String comment;
    private int price;

    public Item(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }

    public int getPrice() {
        return price;
    }
}
