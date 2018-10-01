package es.unavarra.tlm.pit.botonmorado;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class BotonMorado extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boton_morado);

        findViewById(R.id.botonMorado).setOnClickListener(new ManejadorEnviarAlerta(this, false, false, false));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        SharedPreferences settings = getSharedPreferences("Config", 0);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }






        /**/
        //Tras entrar desde la notificación (app en segundo plano), haría esto y cogería los datos deseados.
        // aquí se decodifican los datos enviados desde la consola de firebase (una notificación,
        // con payload de datos). LIMITACION: solo se ejecuta esto si el usuario entra en la notificación.

        /*DEBUG para el refrescar token
        Button boton =(Button)findViewById(R.id.btnToken);
        boton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Se obtiene el token actualizado
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                Log.d("LOG", "Token actualizado: " + refreshedToken);
            }
        });*/
        /**/


        String nombre = settings.getString("name", "");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.textViewNavHeader1);
        nav_user.setText("¡Hola "+nombre+"!");


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_buscar_alertas) {
            // Handle the camera action
            Intent intent = new Intent(this, BuscarAlertas.class);
            this.startActivity(intent);
            this.finish();

        } else if (id == R.id.menu_donaciones) {
            Intent intent = new Intent(this, Donaciones.class);
            this.startActivity(intent);
            this.finish();

        } else if (id == R.id.menu_condiciones_de_uso) {

            Intent intent = new Intent(this, Condiciones.class);
            this.startActivity(intent);
            this.finish();

        } else if (id == R.id.menu_contacto) {
            Intent intent = new Intent(this, Contacto.class);
            this.startActivity(intent);
            this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Ubicacion UbicacionActual = new Ubicacion();
        UbicacionActual.setBotonMorado(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        //Para que lleve a configuracion si no esta habilitado el gps
       /* if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }*/
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 20, (LocationListener) UbicacionActual);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 20, (LocationListener) UbicacionActual);

       // mlocManager.removeUpdates((LocationListener)UbicacionActual);


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }

    public void setLocation(Location loc) {

        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            SharedPreferences ubicacion = getSharedPreferences("ubicacion",0);
            SharedPreferences.Editor editoru = ubicacion.edit();
            editoru.putString("latitud", String.valueOf(loc.getLatitude()));
            editoru.putString("longitud", String.valueOf(loc.getLongitude()));
            editoru.commit();
            SharedPreferences settings = getSharedPreferences("Config", 0);
            String lat = ubicacion.getString("latitud", "");
            String lon = ubicacion.getString("longitud", "");
            Rest r = new Rest();

            r.enviarLocalizacionSinRespuesta(settings.getString("phone", ""), settings.getString("token", ""), lat, lon, this, this);

        }
        else {

        }

    }




}
