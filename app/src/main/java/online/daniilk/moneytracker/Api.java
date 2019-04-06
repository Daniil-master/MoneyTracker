package online.daniilk.moneytracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

/*
   1) Прочитать про HTTP: GET и POST
   2) Прочитать про JSON
   3) Библиотека Retrofit (http://square.github.io/retrofit/)
   4) Прочесть про конвентеры Retrofit (converter-Gson)
   4) Библиотека OKHttp (https://github.com/square/okhttp)
   5) Имеющейся сервер(getsandbox.com)

  */

    @GET("/items")
  Call<List<Item>> getItems(@Query("type") String type);
}
