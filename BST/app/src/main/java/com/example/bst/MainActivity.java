
package com.example.bst;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private Button mStudent, mDriver, mEmergency, mAbout_us;
    private MapLocation studentLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mStudent = (Button) findViewById(R.id.student);
        mDriver = (Button) findViewById(R.id.driver);
        mEmergency = (Button) findViewById(R.id.emergency);
        mAbout_us = (Button) findViewById(R.id.about_us);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Toast.makeText(getApplicationContext(), "Student Location Success", Toast.LENGTH_SHORT).show();
                        if (location != null) {
                            Double latitude = location.getLatitude();
                            Double longitude = location.getLongitude();
                            studentLocation = new MapLocation(latitude, longitude);
                        }
                    }
                });
            }
            else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION});
            }
        }



       mDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(MainActivity.this, DriverLocationn.class);
                startActivity(intent);
                finish();
                return;*/
                DriverLocation dl = new DriverLocation();
 Log.d(TAG, "onClick: Driver ==> " + dl.driverLocation + " : Student ==> " + studentLocation);
                DisplayTrack(dl.driverLocation, studentLocation);
            }
        });


        mStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DriverLocation dl = new DriverLocation();
                Log.d(TAG, "onClick: Driver ==> " + dl.driverLocation + " : Student ==> " + studentLocation);
                DisplayTrack(dl.driverLocation, studentLocation);
            }
        });
    }

    private void requestPermissions(String[] strings) {
    }

    private void DisplayTrack(MapLocation sourceLocation, MapLocation destinationLocation) {

        try {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=" + sourceLocation + "&daddr=" + destinationLocation));
            startActivity(intent);
        }
        catch (ActivityNotFoundException e) {
            // When google map is not installed initialize uri to playstore
            Uri uri = Uri.parse("https://www.google.com/store/apps/details?id=com.google.android.apps.maps");
            // Initialize Intent with Action View
            Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
            // Set Flag
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            Toast.makeText(getApplicationContext(), "Maps not installed", Toast.LENGTH_SHORT).show();
            startActivity(intent2);
        }
    }
}








































/*
package com.example.bst;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    private Button mStudent, mDriver, mEmergency, mAbout_us;
    private String eStudent;
    public String eDriver;
    private FusedLocationProviderClient fusedLocationProviderClient;

    boolean isPermissionGranted;
    com.example.bst.LocationManager locationManager;
    //private Object LocationManager;

   // LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mStudent = (Button) findViewById(R.id.student);
        mDriver = (Button) findViewById(R.id.driver);
        mEmergency = (Button) findViewById(R.id.emergency);
        mAbout_us = (Button) findViewById(R.id.about_us);


        mDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , DriverLocation.class);
                startActivity(intent);
                finish();
            }
                                   });

        mStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            */
/*    Intent intent = new Intent(MainActivity.this , MapsActivity.class);
                startActivity(intent);
                finish();
                return;*//*

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION )
                    == PackageManager.PERMISSION_GRANTED){
                        fusedLocationProviderClient.getLastLocation()
                                .addOnSuccessListener(new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {
                                        if (location != null) {
                                            Double lat = location.getLatitude();
                                            Double log = location.getLongitude();

                                            eStudent = lat + " , " + log;
                                        }
                                    }
                                });
                    }else{
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION});
                    }
                }

                DriverLocation dl = new DriverLocation();

                String eSource = dl.Drivloc;
                String eDestination = eStudent;


                DisplayTrack(eSource , eDestination);

            }


        });

    }

    private void requestPermissions(String[] strings) {
    }

    private void DisplayTrack(String eSource , String eDestination){

        try {
            // When Map is installed in device then it will redirect to the Google Map
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir"+eSource+ "/" + eDestination);

            // Initialize Intent with Action View
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);

            // Set Package
            intent.setPackage("com.android.apps.maps");


            // Set Flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            // When google map is not installed initialize uri to playstore
            Uri uri = Uri.parse("https://www.google.com/store/apps/details?id=com.google.android.apps.maps");

            // Initialize Intent with Action View
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);

            // Set Flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}*/




