package online.daniilk.moneytracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        adapter = new ItemsAdapter();

        recyclerView = findViewById(R.id.list); // Лучше всех ListView, GridView и др.
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Задаёт как показывать
        recyclerView.setAdapter(adapter); // Связывает данные отрисовки(Что нарисовать)


    }
    /*
         recyclerView.addItemDecoration(); // это инструмент настройки отображения: слева снизу
         CheckBox box = new CheckBox(getApplicationContext()); // на программном уровне, UI. Может не потянуть кастомы активити
         Context информация о приложении, все ресурсы, о настройках и внутренние детали, чем конкретней то точно будет смотрется на экране и лаконично

       RecyclerView:
        1) Подключить
        2) Вставить в Layout
        3) Создать пункт(item)
        4) Обратится к RView и установить LayoutMange
        5) Установить ViewHolder для отрисовки
     */


}
