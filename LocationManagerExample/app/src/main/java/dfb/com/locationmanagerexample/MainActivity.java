package dfb.com.locationmanagerexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private LocationManager locationManager;
    private static final int APP_LOCATION_PERMISSION = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadLocations();
    }

    private void loadLocations() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
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
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null)
            Log.d("MainActivity", "Last Loc: Long" + location.getLongitude() +
                    "Lat" + location.getLatitude());
        locationManager
                .requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0 , this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if(requestCode == APP_LOCATION_PERMISSION){
            if(Manifest.permission.ACCESS_FINE_LOCATION
                    .equals(permissions[0])
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
               &&
                    Manifest.permission.ACCESS_COARSE_LOCATION
                            .equals(permissions[1])
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                loadLocations();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("MainActivity", "Change Loc: Long" + location.getLongitude() +
                                "Lat" + location.getLatitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
