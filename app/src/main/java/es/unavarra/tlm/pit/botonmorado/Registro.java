package es.unavarra.tlm.pit.botonmorado;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;

public class Registro extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        findViewById(R.id.crearCuenta).setOnClickListener(new ComprobarRegistro(
                (EditText)findViewById(R.id.textoNombreRegistro),
                (EditText)findViewById(R.id.textoTelefonoRegistro),
                (CheckBox)findViewById(R.id.checkBoxCondiciones), this));

    }
}
