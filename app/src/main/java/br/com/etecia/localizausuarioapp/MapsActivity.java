package br.com.etecia.localizausuarioapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap m_mapa;

    private Intent m_intent;
    private double m_lat, m_lon;
    /*
    private final String[] m_permissoes = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.INTERNET
    };
    private LocationManager m_location_manager;
    private LocationListener m_location_listener;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /*
        PermissoesActivity.validar_permissoes(m_permissoes, this, 1);
        m_location_manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        m_location_listener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.d("Localização", "onLocationChanged: " + location.toString());
            }
        };
        */
        m_intent = getIntent();
        m_lat = m_intent.getDoubleExtra("m_valor_latitude", 0);
        m_lon = m_intent.getDoubleExtra("m_valor_longitude", 0);

        Toast.makeText(MapsActivity.this,
                "Lat: " + m_lat + " Lon: " + m_lon,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        m_mapa = googleMap;
        m_mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        LatLng m_posicao = new LatLng(m_lat, m_lon);

        CircleOptions m_circulo = new CircleOptions();
        m_circulo.center(m_posicao);
        m_circulo.fillColor(Color.argb(20,0,0,200));
        m_circulo.strokeWidth(10);
        m_circulo.strokeColor(Color.argb(10,0,0,75));
        m_circulo.radius(10000.00);
        m_mapa.addCircle(m_circulo);
        m_mapa.addMarker(new MarkerOptions()
                .position(m_posicao)
                .snippet("Posição escolhida: Lat " + m_lat + " Lon " + m_lon)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon))
                .title("local escolhido"));
        m_mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(m_posicao, 10.5f));
    }
    /*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int m_permissao_resultado : grantResults) {
            if (m_permissao_resultado == PackageManager.PERMISSION_DENIED) {
                validar_usuario();
            } else if (m_permissao_resultado == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    m_location_manager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            0,0,
                            m_location_listener
                    );
                }
            }
        }
    }

    public void validar_usuario() {
        AlertDialog.Builder m_builder = new AlertDialog.Builder(this);

        m_builder.setTitle("Permissão negada.");
        m_builder.setMessage("Para utilizar o app é necessário aceitar todas as permissões!");
        m_builder.setCancelable(false);
        m_builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog m_dialog = m_builder.create();
        m_dialog.show();
    }
    */
}