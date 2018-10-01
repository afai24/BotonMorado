package es.unavarra.tlm.pit.botonmorado;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Donaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donaciones);

        findViewById(R.id.botonDonar).setOnClickListener(new Link(this,"https://www.paypal.me/botonmorado"));
        findViewById(R.id.volver_donaciones).setOnClickListener(new ManejadorOnClick(this, BotonMorado.class));
    }


}
