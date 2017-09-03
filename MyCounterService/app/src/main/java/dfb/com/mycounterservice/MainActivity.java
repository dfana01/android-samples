package dfb.com.mycounterservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener, MyCounterService.MyCounterListener{

    private Button btnStart;
    private TextView lblText;
    private TextView lblCurrentValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MyCounterService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

        btnStart = (Button) findViewById(R.id.btn_start);
        lblText = (TextView) findViewById(R.id.lbl_text);
        lblCurrentValue = (TextView) findViewById(R.id.lbl_current_value);
        btnStart.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
        super.onDestroy();
    }

    private MyCounterService.MyCounterBinder binder;
    private MyCounterService myCounterService;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if(iBinder instanceof MyCounterService.MyCounterBinder)
                myCounterService = ((MyCounterService.MyCounterBinder) iBinder).getService();
                myCounterService.setCounterListener(MainActivity.this);
                binder = (MyCounterService.MyCounterBinder) iBinder;
            Log.d("MaintActivity","Services Connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("MaintActivity","Services Disconnected");
        }
    };

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MyCounterService.class);
        if(binder.isRunning()) {
            btnStart.setText("Start");
            lblText.setText(String.format("My Counter Number Is: %d ",binder.getCounter()));
            intent.setAction(MyCounterService.STOP_COUNTER);
        } else{
            lblText.setText("My counter is running");
            btnStart.setText("Stop");
            intent.setAction(MyCounterService.START_COUNTER);
        }
        startService(intent);
    }

    @Override
    public void currentValue(Integer counter) {
        lblCurrentValue.setText(counter.toString());
    }
}
