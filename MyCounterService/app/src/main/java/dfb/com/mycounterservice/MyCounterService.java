package dfb.com.mycounterservice;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class MyCounterService extends Service implements Runnable{

    private MyCounterBinder mBinder = new MyCounterBinder();
    public static final String START_COUNTER = "START_COUNTER";
    public static final String STOP_COUNTER = "STOP_COUNTER";

    private Integer counter;
    private boolean running;
    private MyCounterListener listener;

    private Handler mhandler;

    private NotificationManagerCompat notificationManagerCompat;

    public MyCounterService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mhandler = new Handler(Looper.myLooper());
        notificationManagerCompat = NotificationManagerCompat.from(this);
        counter = 0;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) return START_STICKY;

        if (START_COUNTER.equals(intent.getAction())){
            running = true;
            mhandler.post(this);
        } else if (STOP_COUNTER.equals(intent.getAction())){
            mhandler.removeCallbacks(this);
            running = false;
            publishNotification();
        }
        return START_STICKY;
    }

    @Override
    public void run() {
        counter++;
        mhandler.postDelayed(this, 500);
        if(this.listener != null){
            new Handler(getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    listener.currentValue(counter);
                }
            });
        }
    }

    public class MyCounterBinder extends Binder {
        public MyCounterService getService(){
            return MyCounterService.this;
        }

        public Integer getCounter(){
            return counter;
        }

        public boolean isRunning(){
            return running;
        }
    }

    public void setCounterListener(MyCounterListener listener){
        this.listener = listener;
    }

    public interface MyCounterListener{
        void currentValue(Integer counter);
    }

    private void publishNotification(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Example")
                .setContentText("My counter is " + counter);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(contentIntent);
        notificationManagerCompat.notify(0, mBuilder.build());
    }
}
