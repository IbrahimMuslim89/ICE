package com.example.ice;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    //___________________________________________________________________________________________________
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onMapRead: Ready");
    mMap = googleMap;



    if(mLocationPermissionsGranted){
        getDeviceLocation();

        mMap.setMyLocationEnabled(true);
        init();

        }
    }
    //___________________________________________________________________________________________________

    private static final String TAG = "MapActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15;
    //vars
    private boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;


    //___________________________________________________________________________________________________
    private void initMap(){
        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapActivity.this);
        Log.d(TAG,"initMap: Ready");
    }
    //___________________________________________________________________________________________________
    //Widgets
    private EditText mSearchText;
    private ImageView mGPS;






    //___________________________________________________________________________________________________
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mSearchText = (EditText) findViewById(R.id.input_search);
        mGPS = (ImageView) findViewById(R.id.ic_gps);
        getLocationPermission();


    }
    //___________________________________________________________________________________________________
    private void init(){

        Log.d(TAG,"init: initializing");
        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                hideKeyboard();
                if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                            ||  keyEvent.getAction() == KeyEvent.KEYCODE_ENTER){

                    //Execute our Method for  Search
                    geoLocate();
                    hideKeyboard();


                }

                return false;
            }
        });
        mGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: GPS button Clicked");
                getDeviceLocation();
                hideKeyboard();
            }
        });




        hideKeyboard();
    }

    //___________________________________________________________________________________________________


            private void geoLocate(){
                hideKeyboard();
                Log.d(TAG, "geoLocate : GeoLocate");
                 String searchString = mSearchText.getText().toString();
                Geocoder geocoder = new Geocoder(MapActivity.this);
                List<Address> list = new ArrayList<>();

                try {
                        list = geocoder.getFromLocationName(searchString,1);

                    }catch (IOException e){
                      Log.d(TAG,"GeoLocate: IOException" + e.getMessage());


                    }

                    if(list.size()>0){
                        Address address = list.get(0);
                        Log.d(TAG,"geoLocate: Found a location" + address.toString());
                        // Toast.makeText(address.toString(), "", Toast.LENGTH_SHORT).show();

            //__________________________________________________
              moveCamera(new LatLng(address.getLatitude(),address.getLongitude()),DEFAULT_ZOOM,address.getAddressLine(0));
                        hideKeyboard();
                    }
             }

    //___________________________________________________________________________________________________






    //___________________________________________________________________________________________________
    private void getDeviceLocation()
    {hideKeyboard();
        Log.d(TAG,"GetDeviceLocation:Getting CurrentLocation");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionsGranted)
            {
                final Task location = mFusedLocationProviderClient.getLastLocation();
              location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: Current Location Loaded");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),DEFAULT_ZOOM,"My Location");




                        } else {
                            Log.d(TAG, "onComplete: Current Location Null");

                        }
                    }

              });
            }

        }catch (SecurityException e)
        {
            Log.e(TAG,"getDeviceLocation : SecurityException" + e.getMessage() );

        }

    }
    //___________________________________________________________________________________________________
    private void moveCamera(LatLng latLng,Float zoom,String title){
        hideKeyboard();
        Log.d(TAG,"moveCamera:Moving camera to Lat:" + latLng.latitude + ", lng:" + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));

        if(!title.equals("My Location")){
            MarkerOptions options = new MarkerOptions().position(latLng).title(title);
            mMap.addMarker(options);


        }
        hideKeyboard();


    }
    //___________________________________________________________________________________________________
    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                initMap();
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);


        }
        Log.d(TAG,"GetLocationPermission: Ready");
    }
    //___________________________________________________________________________________________________
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG,"GetLocationPermission: Failed");
                            return;
                        }
                    }
                    mLocationPermissionsGranted = true;
                    Log.d(TAG,"GetRequestPermission: Ready");
                    //Initialize our map
                    initMap();


                }
            }

        }
    }
    private void hideKeyboard(){

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);
    }

}


