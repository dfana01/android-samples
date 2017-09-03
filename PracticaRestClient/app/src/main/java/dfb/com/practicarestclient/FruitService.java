package dfb.com.practicarestclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Dante on 8/5/2017.
 */

public interface FruitService {
    @GET("example/practica.json")
    Call<List<Item>> getItems();
}
