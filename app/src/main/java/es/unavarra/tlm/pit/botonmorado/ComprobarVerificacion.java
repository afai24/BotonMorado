package es.unavarra.tlm.pit.botonmorado;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ds on 04/11/2017.
 */

public class ComprobarVerificacion implements View.OnClickListener{

    TextView telefono;
    EditText codigo;
    Activity actual;

    public ComprobarVerificacion(TextView telefono, EditText codigo, Activity actual) {
        this.telefono=telefono;
        this.codigo=codigo;
        this.actual=actual;
    }

    @Override
    public void onClick(View v) {

        String textoTelefonoTodo = telefono.getText().toString();
        String partes[] = textoTelefonoTodo.split(" ");
        String textoTelefono = partes[1];
        String textoCodigo = codigo.getText().toString();

        Context context = v.getContext();
        int duration = Toast.LENGTH_SHORT;

        if (textoTelefono.equals("")){
            CharSequence texto = "Rellena el campo de telefono";
            Toast toast = Toast.makeText(context, texto, duration);
            toast.show();
        }else{
            if (textoCodigo.equals("")) {
                CharSequence texto = "Rellena el campo de codigo";
                Toast toast = Toast.makeText(context, texto, duration);
                toast.show();
            }else{
                Rest r = new Rest();
                r.enviarVerificacion(textoTelefono, textoCodigo, context, actual);
            }
        }

    }

}
