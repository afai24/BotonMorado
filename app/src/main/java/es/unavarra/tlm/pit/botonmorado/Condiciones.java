package es.unavarra.tlm.pit.botonmorado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Condiciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condiciones);

        findViewById(R.id.volver_condiciones).setOnClickListener(new ManejadorOnClick(this, BotonMorado.class));

    }
}
