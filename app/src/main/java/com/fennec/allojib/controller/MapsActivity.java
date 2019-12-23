package com.fennec.allojib.controller;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fennec.allojib.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    public static MapsActivity main;
    public static  GoogleMap mMap;

    public TextView tv_maps;
    public Button btn_maps;

    LocationManager locationManager ;
    boolean GpsStatus ;

    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;
    public TextView latTextView, lonTextView;

    public double Latitude ;
    public double Longitude ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        this.setTitle("Choisir votre Adresse");
        main = this;

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        btn_maps = (Button) findViewById(R.id.btn_maps);
        tv_maps = (TextView) findViewById(R.id.tv_maps);


        CheckGpsStatus();

        btn_maps.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent1);
            }
        });

        latTextView = findViewById(R.id.latTextView);
        lonTextView = findViewById(R.id.lonTextView);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        getLastLocation();

    }

    private void getLastLocation()
    {
        if (checkPermissions())
        {
            if (isLocationEnabled())
            {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<Location> task)
                            {
                                Location location = task.getResult();
                                if (location == null)
                                {
                                    requestNewLocationData();
                                } else {
                                    latTextView.setText(location.getLatitude()+"");
                                    lonTextView.setText(location.getLongitude()+"");

                                    Latitude = location.getLatitude();
                                    Longitude = location.getLongitude();

                                    /**************** maps to adress ****/

                                    Geocoder geocoder;
                                    List<Address> addresses;
                                    geocoder = new Geocoder(main, Locale.getDefault());

                                    try {
                                        addresses = geocoder.getFromLocation(Latitude, Longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                        String city = addresses.get(0).getLocality();
                                        String state = addresses.get(0).getAdminArea();
                                        String country = addresses.get(0).getCountryName();
                                        String postalCode = addresses.get(0).getPostalCode();
                                        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                                        tv_maps.setText(address);

                                    }catch (Exception e) {

                                    }
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }



    private void requestNewLocationData()
    {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates( mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback()
    {
        @Override
        public void onLocationResult(LocationResult locationResult)
        {
            Location mLastLocation = locationResult.getLastLocation();
            latTextView.setText(mLastLocation.getLatitude()+"");
            lonTextView.setText(mLastLocation.getLongitude()+"");
        }
    };

    private boolean checkPermissions()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }
        return false;
    }

    private void requestPermissions()
    {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled()
    {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        /*if (checkPermissions()) {
            getLastLocation();
        }*/
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        // define point to center on

        LatLngBounds TAZA = new LatLngBounds(
                new LatLng(34.213202, -4.001011), new LatLng(34.236396, -4.015436));

        LatLng origin = new LatLng(34.236396, -4.015436);

        CameraUpdate panToOrigin = CameraUpdateFactory.newLatLng(origin);
        mMap.moveCamera(panToOrigin);



        // set zoom level with animation
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 400, null);

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng latLng)
            {
                mMap.clear();

                Latitude = latLng.latitude;
                Longitude = latLng.longitude;

                LatLng mylocation = new LatLng(latLng.latitude, latLng.longitude);
                mMap.addMarker(new MarkerOptions().position(mylocation).title("location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));

                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(main, Locale.getDefault());

                try
                {
                    addresses = geocoder.getFromLocation(Latitude, Longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                    tv_maps.setText(address);

                    Log.d("TAG_MAPS", "Pause marker in here "+address);

                }catch (Exception e)
                {

                }
            }
        });
    }

    public void CheckGpsStatus()
    {
        locationManager = (LocationManager) main.getSystemService(main.LOCATION_SERVICE);
        assert locationManager != null;
        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(GpsStatus == true)
        {
            tv_maps.setText("GPS est activé");
        } else {
            tv_maps.setText("GPS est désactivé");
        }
    }
}
