package online.daniilk.moneytracker;

public interface ItemsAdapterListener {
    void onItemClick(Item item, int position);
    void onItemLong(Item item, int position);
}
