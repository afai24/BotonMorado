package es.unavarra.tlm.pit.botonmorado;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class Verificacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion);

        SharedPreferences settings = getSharedPreferences("Config", 0);
        TextView telefono = (TextView)findViewById(R.id.textoTelefonoVerificacion);
        telefono.setText("Teléfono: "+settings.getString("phone", ""), TextView.BufferType.EDITABLE);
        findViewById(R.id.botonVerificar).setOnClickListener(new ComprobarVerificacion(telefono, (EditText)findViewById(R.id.textoCodigoVerificacion), this));
    }
}
