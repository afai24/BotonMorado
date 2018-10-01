package es.unavarra.tlm.pit.botonmorado;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.Normalizer;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ds on 04/11/2017.
 */

public class ComprobarRegistro implements View.OnClickListener{

    EditText nombre, telefono;
    CheckBox condiciones;
    Activity actual;

    public ComprobarRegistro(EditText nombre, EditText telefono, CheckBox condiciones, Activity actual) {
        this.nombre=nombre;
        this.telefono=telefono;
        this.condiciones=condiciones;
        this.actual=actual;
    }

    @Override
    public void onClick(View v) {

        String textoNombre = java.text.Normalizer.normalize(nombre.getText().toString(), java.text.Normalizer.Form.NFD);
        String textoTelefono = telefono.getText().toString();
        boolean checkboxCondiciones = condiciones.isChecked();

        Context context = v.getContext();
        int duration = Toast.LENGTH_SHORT;

        if (textoNombre.equals("")){
            CharSequence texto = "Rellena el campo de nombre";
            Toast toast = Toast.makeText(context, texto, duration);
            toast.show();
        }else{
            if (textoTelefono.equals("")){
                CharSequence texto = "Rellena el campo de telefono";
                Toast toast = Toast.makeText(context, texto, duration);
                toast.show();
            }else if (!checkboxCondiciones){
                CharSequence texto = "Debes aceptar las condiciones de uso";
                Toast toast = Toast.makeText(context, texto, duration);
                toast.show();
            }else{
                SharedPreferences settings = context.getSharedPreferences("Config", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("name", textoNombre);
                editor.putString("phone", textoTelefono);
                editor.commit();
                Rest r = new Rest();
                r.enviarRegistro(textoNombre, textoTelefono, context, actual);
            }
        }

    }

}
