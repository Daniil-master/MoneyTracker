package online.daniilk.moneytracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    private static final String TAG = "ItemAdapter";
    private List<Item> data = new ArrayList<>();

    public ItemsAdapter() {
        createData();
    }

    @Override
    public ItemsAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ItemViewHolder holder, int position) {
        // Даёт данные для наполнения
       Item item = data.get(position);
        holder.applyData(item);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


     static class ItemViewHolder extends RecyclerView.ViewHolder {
        // Вложенный статистический класс
        private final TextView title;
        private final TextView price;

        // Это связь между элементом списка его визуального представления
        // Обёртка View которая может содержать логику
        public ItemViewHolder(@NonNull View itemView) {
            // Держатель Вида - держит в себе отрисовку и меняет при скроле
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);

        }

        public void applyData(Item item) {
            title.setText(item.getTitle()); // Сдесь добавить рубль
            price.setText(String.valueOf(item.getPrice() + (R.string.currency_rub)));
            SpannableString spannableString = new SpannableString(String.valueOf(item.getPrice()));

        }

    }

    private void createData() {
        data.add(new Item("Молоко", 35));
        data.add(new Item("Жизнь", 1));
        data.add(new Item("Курсы", 50));
        data.add(new Item("Хлеб", 26));
        data.add(new Item("Тот самый ужин который я оплатил за всех потому что платил картой", 600000));
        data.add(new Item("", 0));
        data.add(new Item("Тот самый ужин", 604));
        data.add(new Item("ракета Falcon Heavy", 1));
        data.add(new Item("Лего Тысячилетний сокол", 100000000));
        data.add(new Item("Монитор", 100));
        data.add(new Item("MacBook Pro", 100));
        data.add(new Item("Шиколад", 100));
        data.add(new Item("Шкаф", 100));
    }


    // Ctrl + F - поиск
    // Ctrl + R - замена
}

