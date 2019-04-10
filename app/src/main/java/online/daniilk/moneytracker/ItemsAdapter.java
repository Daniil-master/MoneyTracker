package online.daniilk.moneytracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import online.daniilk.moneytracker.api.Item;

class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    private static final String TAG = "ItemsAdapter";

    private List<Item> data = new ArrayList<>();
    private ItemsAdapterListener listener = null;

    public void setListener(ItemsAdapterListener listener) {
        this.listener = listener;
    }

    //    public ItemsAdapter() {
////        createData();
//    }

    public void setData(List<Item> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addItem(Item item) {
        data.add(0, item);
        notifyItemInserted(0);
    }

    @Override
    public ItemsAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);
        // (разметка, родитель, изменнение в процессе контроллера реагирование к корню)
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ItemViewHolder holder, int position) {
        // Даёт данные для наполнения
        Item item = data.get(position);
        holder.bind(item, position, listener, selections.get(position, false));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    private SparseBooleanArray selections = new SparseBooleanArray();
// Более оптимизированное чем HashMap, от Google

    public void toggleSelection(int position) {

        if (selections.get(position, false)) {
            selections.delete(position);
        } else {
            selections.put(position, true);
        }
        notifyItemChanged(position);

    }

    void clearSelections() {
        selections.clear();
        notifyDataSetChanged();
    }

    int getSelectedItemCount() {
        return selections.size();
    }

    List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selections.size());
        for (int i = 0; i < selections.size(); i++) {
            items.add(selections.keyAt(i));
        }
        return items;
    }
    Item remove(int pos){
        final Item item = data.remove(pos);
        notifyItemRemoved(pos);
        return item;
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

        public void bind(final Item item, final int position, final ItemsAdapterListener listener, boolean selected) {
            title.setText(item.name);
            price.setText(item.price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(item, position);
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) {
                        listener.onItemLong(item, position);
                    }
                    return true;
                }
            });
            itemView.setActivated(selected);
        }

    }


    // Ctrl + F - поиск
    // Ctrl + R - замена
}

