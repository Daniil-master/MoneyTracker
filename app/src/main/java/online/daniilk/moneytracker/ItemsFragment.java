package online.daniilk.moneytracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemsFragment extends Fragment {

    private static final int TYPE_UNKNOWN = -1;
    public static final int TYPE_INCOMES = 1;
    public static final int TYPE_EXPENSES = 2;

    private static final String TYPE_KEY ="type";

    public static ItemsFragment createItemsFragment(int type){
        ItemsFragment fragment = new ItemsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ItemsFragment.TYPE_KEY, ItemsFragment.TYPE_INCOMES);

        fragment.setArguments(bundle);
        return fragment;
    }


    private int type;

    private RecyclerView recyclerView;
    private ItemsAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ItemsAdapter();

        Bundle bundle = getArguments();
        type = bundle.getInt(TYPE_KEY, TYPE_UNKNOWN);

        if(type == TYPE_UNKNOWN){
            throw new IllegalArgumentException("Unknown type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.list); // Лучше всех ListView, GridView и др.
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // Задаёт как показывать
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
