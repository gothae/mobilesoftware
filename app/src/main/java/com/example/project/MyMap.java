package com.example.project;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitude;
    double longitude;
    String where;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_map);

        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("map_latitude",latitude);
        longitude = intent.getDoubleExtra("map_longitude",longitude);
        where = intent.getStringExtra("name");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng  location = new LatLng(latitude,longitude);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(location);
        markerOptions.title(where);
        markerOptions.snippet("지금 있는 곳");
        mMap.addMarker(markerOptions);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
}
// 제주도 33.499602, 126.531262
// 서울  37.566670, 126.978423
