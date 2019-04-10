package online.daniilk.moneytracker;

import online.daniilk.moneytracker.api.Item;

public interface ItemsAdapterListener {
    void onItemClick(Item item, int position);
    void onItemLong(Item item, int position);
}
