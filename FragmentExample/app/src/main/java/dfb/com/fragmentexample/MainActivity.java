package dfb.com.fragmentexample;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements BlueFragment.OnFragmentInteractionListener,
                    RedFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_blue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.lyt_fragment,BlueFragment.newInstance("1","2"))
                        .addToBackStack(null)
                        .commit();
            }
        });

        findViewById(R.id.btn_red).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.lyt_fragment,RedFragment.newInstance("3","4"))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onRedFragmentButtonClick() {
        Toast.makeText(this, "Click Red Button", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBlueFragmentButtonClick() {
        Toast.makeText(this, "Click Blue Button", Toast.LENGTH_SHORT).show();
    }
}
