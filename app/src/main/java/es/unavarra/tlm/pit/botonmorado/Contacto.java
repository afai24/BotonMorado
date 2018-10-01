package es.unavarra.tlm.pit.botonmorado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Contacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        findViewById(R.id.twitter).setOnClickListener(new Link(this,"https://www.twitter.com/BotonMoradoApp"));
        findViewById(R.id.instagram).setOnClickListener(new Link(this,"https://www.instagram.com/BotonMoradoApp"));
        findViewById(R.id.facebook).setOnClickListener(new Link(this,"https://www.facebook.com/boton.morado.92"));
        findViewById(R.id.volver_contacto).setOnClickListener(new ManejadorOnClick(this, BotonMorado.class));
    }
}
