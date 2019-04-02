package online.daniilk.moneytracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {

    private static final String TAG = "ItemListActivity";
    private RecyclerView recyclerView;
    private List<Record> data = new ArrayList<>();
    private ItemListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        recyclerView = findViewById(R.id.list); // Лучше всех ListView, GridView и др.
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Задаёт как показывать
        adapter = new ItemListAdapter();
        createData();
        recyclerView.setAdapter(adapter); // Связывает данные отрисовки(Что нарисовать)

//        recyclerView.addItemDecoration(); // это инструмент настройки отображения: слева снизу
//        CheckBox box = new CheckBox(getApplicationContext()); // на программном уровне, UI. Может не потянуть кастомы активити
        // Context информация о приложении, все ресурсы, о настройках и внутренние детали, чем конкретней то точно будет смотрется на экране и лаконично

    }
    /*
       RecyclerView:
       1) Подключить
       2) Вставить в Layout
       3) Создать пункт(item)
       4) Обратится к RView и установить LayoutMange
       5) Установить ViewHolder для отрисовки
     */

    private void createData() {
        data.add(new Record("Молоко", 35));
        data.add(new Record("Жизнь", 1));
        data.add(new Record("Курсы", 50));
        data.add(new Record("Хлеб", 26));
        data.add(new Record("Тот самый ужин который я оплатил за всех потому что платил картой", 600000));
        data.add(new Record("", 0));
        data.add(new Record("Тот самый ужин", 604));
        data.add(new Record("ракета Falcon Heavy", 1));
        data.add(new Record("Лего Тысячилетний сокол", 100000000));
        data.add(new Record("Монитор", 100));
        data.add(new Record("MacBook Pro", 100));
        data.add(new Record("Шиколад", 100));
        data.add(new Record("Шкаф", 100));
    }

    private class ItemListAdapter extends RecyclerView.Adapter<RecordViewHolder> {


        @Override
        public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateViewHolder() " + recyclerView.getChildCount());
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_record, parent, false);
            return new RecordViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecordViewHolder holder, int position) {
            // Даёт данные для наполнения
            Log.d(TAG, "onBindViewHolder() " + recyclerView.getChildCount() + " " + position);
            Record record = data.get(position);
            holder.applyData(record);

        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    private class RecordViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView price;

        // Это связь между элементом списка его визуального представления
        // Обёртка View которая может содержать логику
        public RecordViewHolder(@NonNull View itemView) {
            // Держатель Вида - держит в себе отрисовку и меняет при скроле
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);

        }

        public void applyData(Record record) {
            Log.d(TAG, "onCreateViewHolder " + recyclerView.getChildLayoutPosition(itemView) + " " + record.getTitle());
            title.setText(record.getTitle()); // Сдесь добавить рубль
            price.setText(String.valueOf(record.getPrice()));
            SpannableString spannableString = new SpannableString(String.valueOf(record.getPrice()));

        }

    }
}
