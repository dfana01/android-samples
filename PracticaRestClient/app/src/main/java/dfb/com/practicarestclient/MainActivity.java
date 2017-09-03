package dfb.com.practicarestclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://vinrosa.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FruitService fruitService = retrofit.create(FruitService.class);
        fruitService.getItems().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                List<Item> items = response.body();
                Collections.sort(items);
                rv.setAdapter(new RecyclerAdapter(MainActivity.this, items));
                Log.d("MainActivity",String.format("Response %s",response.body()));
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.d("MainActivity",String.format("Response %s",t));
            }
        });


    }
}
