package es.unavarra.tlm.pit.botonmorado;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BuscarAlertas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_alertas);

        findViewById(R.id.botonQuieroAyudar).setOnClickListener(new ManejadorBuscarAlertas(this, false));

        findViewById(R.id.volver_buscar_alertas).setOnClickListener(new ManejadorOnClick(this, BotonMorado.class));

    }
}
