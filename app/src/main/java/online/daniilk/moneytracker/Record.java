package online.daniilk.moneytracker;

public class Record {

    private String title; // private - защещённость
    private String comment;
    private int price;

    public Record(String title, int price) {
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
