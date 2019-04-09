package online.daniilk.moneytracker.api;

import java.util.List;

import online.daniilk.moneytracker.Item;
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
    // @GET("auth")
    //  Call<AuthResult> auth(@Query("social_user_id") String userId);

    @GET("items")
    Call<List<Item>> getItems(@Query("type") String type);
//    Call<List<Item>> getItems(@Query("type") String type);

    //@POST("items/add")
    // Call<AddItemResult> addItem(@Query("price") String price, @Query("name") String name, @Query("type") String type);

    // ?price=10&name<=name>&type<="expense","income"
    // со стороны сервера принимаемые данные

//    @GET("balance")
//    Call<BalanceResult> balance();

}
