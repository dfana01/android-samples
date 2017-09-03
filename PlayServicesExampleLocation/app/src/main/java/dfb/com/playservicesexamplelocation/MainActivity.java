package dfb.com.playservicesexamplelocation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationProvider;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient locationProviderClient;
    private static final int APP_LOCATION_PERMISSION = 12345;
    private LocationCallback locationCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLastLocation();
    }

    private void getLastLocation() {
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat
                .checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    APP_LOCATION_PERMISSION);
            return;
        }

        locationCallBack = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Location last = locationResult.getLastLocation();
                List<Location> locationList = locationResult.getLocations();
                printLastLocation("Last Location Update", last);
                for(Location location:locationList){
                    printLastLocation("Extra Location",location);
                }
            }
        };


        locationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.getResult() == null)
                    return;
                Location result  = task.getResult();
                printLastLocation("Last Location",result);
            }
        });

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);

        locationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null);
    }

    private void printLastLocation(String prefix, Location result) {
        Log.d("MainActivity", prefix+result.getLatitude() + ","+ result.getLongitude());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(APP_LOCATION_PERMISSION == requestCode){
            boolean locationEnable = true;
            int i = 0;
            for(String permission: permissions){
                locationEnable &= ( (Manifest.permission.ACCESS_FINE_LOCATION.equals(permission)
                                        || Manifest.permission.ACCESS_COARSE_LOCATION.equals(permission))
                                    && grantResults[i] == PackageManager.PERMISSION_GRANTED)?
                                true:false;
            }
            if(locationEnable)
                getLastLocation();
        }
    }
}
