package edu.northeastern.numadsp_23vinhk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.pm.PackageManager;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class Location_Activity extends AppCompatActivity {



    LocationManager locationManager;
    final static int REQUEST_CODE = 1;

    TextView longtitudetxtview, latitudetxtview, totalDistancetxtview;


    private float totaldistance;


    private Location previousLocation;

    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        longtitudetxtview = findViewById(R.id.longitude);
        reset = findViewById(R.id.reset_distance);
        totalDistancetxtview = findViewById(R.id.total_distance);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        latitudetxtview = findViewById(R.id.latitude);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDistance();
            }
        });

        if (savedInstanceState != null) {

            totaldistance = savedInstanceState.getFloat("totaldistance");
            totalDistancetxtview.setText("distance moved: " + totaldistance+" "+   "metres" );
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        getLocationUpdates();
    }

    public void getLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastKnownLocation != null) {
            double latitude = lastKnownLocation.getLatitude();
            double longitude = lastKnownLocation.getLongitude();
            latitudetxtview.setText(("Latitude: " + latitude));
            longtitudetxtview.setText(("Longitude:" + longitude));
            previousLocation = lastKnownLocation;
        }
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            latitudetxtview.setText(("Latitude: " + latitude));
            longtitudetxtview.setText(("Longtitude:" + longitude));

            float distance = 0;
            if (previousLocation != null) {
                distance = previousLocation.distanceTo(location);
            }
            totaldistance += distance;
            totalDistancetxtview.setText("distance moved  :" + totaldistance  +" "+   "metres" );
            previousLocation = location;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    private void updateDistance() {
        totaldistance = 0;
        totalDistancetxtview.setText("distance moved :" + totaldistance  + " "+   "metres" );
        previousLocation = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocationUpdates();
            } else {
                Toast.makeText(this, "permission not given", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("EXIT LOCATION ACTIVITY")
                .setMessage("ARE YOU SURE YOU WANT TO TERMINATE WHILE DISTANCE BEING CALCULATED")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("NO", null)
                .show();


    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putFloat("totaldistance", totaldistance);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

}

