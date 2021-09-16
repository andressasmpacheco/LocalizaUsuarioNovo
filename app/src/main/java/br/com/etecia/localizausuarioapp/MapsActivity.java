package br.com.etecia.localizausuarioapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int ilat;
    private int ilongi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude", ilat);
        double longitude = intent.getDoubleExtra("longitude", ilongi);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


        // Add a marker in Sydney and move the camera
        LatLng posicao= new LatLng( ilat, ilongi);
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(posicao);
        circleOptions.fillColor(Color.argb(50,0,255,255));
        circleOptions.strokeWidth(10);
        circleOptions.strokeColor(Color.argb(50,47,79,79));
        //Medida em metros
        circleOptions.radius(10000.00);
        //Aplicando o circulo no mapa
        mMap.addCircle(circleOptions);
        mMap.addMarker(new MarkerOptions()
                .position(posicao)
                .snippet("Local escolhido")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon))
                .title("local escolhido"));

        mMap.addMarker(new MarkerOptions().position(posicao).title("O local é: "+ilat+ilongi));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posicao));
    }
}