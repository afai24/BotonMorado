package es.unavarra.tlm.pit.botonmorado;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView mTextMessage;
    private ScrollView textCondiciones;
    private InteractiveScrollView tutorial;
    private Button botonRegistrarse;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_tutorial:
                    mTextMessage.setText(R.string.tutorial1);
                    botonRegistrarse.setVisibility(View.INVISIBLE);
                    textCondiciones.setVisibility(View.INVISIBLE);
                    tutorial.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_condiciones_de_uso:
                    mTextMessage.setText(R.string.condiciones_de_uso);
                    botonRegistrarse.setVisibility(View.INVISIBLE);
                    textCondiciones.setVisibility(View.VISIBLE);
                    tutorial.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_registrarse:
                    mTextMessage.setText(R.string.tutorial2);
                    botonRegistrarse.setVisibility(View.VISIBLE);
                    textCondiciones.setVisibility(View.INVISIBLE);
                    tutorial.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences settings = getSharedPreferences("Config", 0);
        String token = settings.getString("token", "");
        String phone = settings.getString("phone","");





        if(token=="") {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //textCondiciones.setMovementMethod(new ScrollingMovementMethod());
            final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            tutorial = (InteractiveScrollView) findViewById(R.id.scroll_tutorial);
            tutorial.setOnBottomReachedListener(
                    new InteractiveScrollView.OnBottomReachedListener() {
                        @Override
                        public void onBottomReached() {
                           navigation.setVisibility(View.VISIBLE);
                        }
                    }
            );
            textCondiciones = (ScrollView) findViewById(R.id.scroll);
            mTextMessage = (TextView) findViewById(R.id.message);
            botonRegistrarse = (Button) findViewById(R.id.buttonTutorial);
            botonRegistrarse.setVisibility(View.INVISIBLE);
            textCondiciones.setVisibility(View.INVISIBLE);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            botonRegistrarse.setOnClickListener(new ManejadorOnClick(this, Registro.class));
        }else{
            super.onCreate(savedInstanceState);
            Intent intent = new Intent(this, BotonMorado.class);
            this.startActivity(intent);
            this.finish();




        }
    }









}
