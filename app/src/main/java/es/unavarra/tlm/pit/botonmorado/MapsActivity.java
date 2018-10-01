package es.unavarra.tlm.pit.botonmorado;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    String latitudA;
    String longitudA;
    Double lat;
    Double lon;

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SharedPreferences ubicacion = getSharedPreferences("coordenadasAyuda",0);
        latitudA = ubicacion.getString("latitudA", "");
        longitudA = ubicacion.getString("longitudA", "");
        lat = Double.parseDouble(latitudA);
        lon = Double.parseDouble(longitudA);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng posicion = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(posicion).title("Necesito Ayuda"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 15));

    }
}