package com.example.apostolis.katanemhmena;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap= googleMap;
        LatLng centerofdatabase = new LatLng(40.8206, -73.9497);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(centerofdatabase));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 1267, null);
        final Button coordinates = (Button) findViewById(R.id.button);
        coordinates.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GotoActivity2(v);
            }
        });

        final Button check=(Button) findViewById(R.id.checkin);
        check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GotoActivity3(v);
            }
        });
    }

    public void GotoActivity3(View v){
        Intent intent = new Intent(MapsActivity.this, Activity3.class);
        startActivity(intent);
    }

    public void GotoActivity2(View v){
        Intent intent = new Intent(MapsActivity.this, Activity2.class);
        startActivity(intent);
    }

    public static GoogleMap getmMap() {
        return mMap;
    }
}
