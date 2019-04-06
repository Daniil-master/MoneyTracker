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

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsFragment extends Fragment {

    private static final String TAG = "ItemsFragment";
    private static final String TYPE_KEY = "type";

    public static ItemsFragment createItemsFragment(String type) {
        ItemsFragment fragment = new ItemsFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ItemsFragment.TYPE_KEY, type);

        fragment.setArguments(bundle);
        return fragment;
    }


    private String type;

    private RecyclerView recyclerView;
    private ItemsAdapter adapter;
    private Api api;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ItemsAdapter();

        Bundle bundle = getArguments();

        assert bundle != null;
        type = bundle.getString(TYPE_KEY, Item.TYPE_EXPENSES);

        if (type.equals(Item.TYPE_UNKNOWN)) {
            throw new IllegalArgumentException("Unknown type");
        }

        api = ((App) Objects.requireNonNull(getActivity()).getApplication()).getApi();
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

        loadItems();
    }

     private void loadItems(){
         Call<List<Item>> call = api.getItems(type);

         call.enqueue(new Callback<List<Item>>() {
             @Override
             public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                 // придёт ответ
                 adapter.setData(response.body());
             }

             @Override
             public void onFailure(Call<List<Item>> call, Throwable t) {
                 // произойдёт ошибка

             }
         });
     }

   // Нельзя утечка памяти и последовательные потоки(устарел). Так же Loader.
//    private void loadItems() {
//        AsyncTask<Void, Void, List<Item>> task = new AsyncTask<Void, Void, List<Item>>() {
//
//            @Override
//            protected void onPreExecute() {
//                Log.d(TAG, "onPreExecute: thread name = " + Thread.currentThread().getName());
//            }
//
//            @Override
//            protected List<Item> doInBackground(Void... voids) {
//                Log.d(TAG, "onPreExecute: thread name = " + Thread.currentThread().getName());
//                Call<List<Item>> call = api.getItems(type);
//
//                try {
//                    List<Item> items = call.execute().body();
//
//                    return items;
//                    // работает не только ко
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(List<Item> items) {
//                if (items != null)
//                    adapter.setData(items);
//            }
//        };
//
//        task.execute();
//    }
    // Thread and Handler

//    private void loadItems() {
//        Log.d(TAG, "loadItems: current thread " + Thread.currentThread().getName());
//        new LoadItemsTask(new Handler(callback)).start();
//
//    }
//
//    private Handler.Callback callback = new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            if (msg.what == DATA_LOADED) {
//                List<Item> items = (List<Item>) msg.obj;
//                adapter.setData(items);
//            }
//            return true;
//        }
//    };
//
//    private final static int DATA_LOADED = 123;
//
//
//    private class LoadItemsTask implements Runnable {
//        private Thread thread;
//        private Handler handler;
//
//        public LoadItemsTask(Handler handler) {
//            // Handler - это клас который отправляет на главный поток новое сообщение
//            // Главный поток зациклен, обычный бы выполнил и умер,
//            // что бы он жил вечно, обрабатывает сообщения
//            thread = new Thread(this);
//            this.handler = handler;
//        }
//
//        public void start() {
//            thread.start();
//        }
//
//        @Override
//        public void run() {
//            Log.d(TAG, "run: current thread " + Thread.currentThread().getName());
//            Call<List<Item>> call = api.getItems(type);
//
//            try {
//                List<Item> items = call.execute().body();
//                handler.obtainMessage(DATA_LOADED, items).sendToTarget();
//                // работает не только ко
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    /*
         adapter.notifyDataSetChanged();  Ctrl+Shift+A горячие клавиши

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
