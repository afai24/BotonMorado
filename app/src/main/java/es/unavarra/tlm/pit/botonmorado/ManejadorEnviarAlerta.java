package es.unavarra.tlm.pit.botonmorado;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ds on 23/11/2017.
 */

public class ManejadorEnviarAlerta implements View.OnClickListener{

    private Activity activity;
    private boolean enviar;
    private boolean parar;
    private boolean volver;

    public ManejadorEnviarAlerta(Activity activity, boolean enviar, boolean parar, boolean volver) {
        this.activity = activity;
        this.enviar = enviar;
        this.parar = parar;
        this.volver = volver;
    }

    @Override
    public void onClick(View v) {



        if(enviar) {
            //Aqui hay que introducir los valores de latitud y longitud para enviar al servidor
            SharedPreferences ubicacion = v.getContext().getSharedPreferences("ubicacion",0);
            String latitude = ubicacion.getString("latitud", "");
            String longitude = ubicacion.getString("longitud", "");
            //String latitude = "0";
            //String longitude = "0";

            String texto = ((EditText) activity.findViewById(R.id.editTextPedirAyuda)).getText().toString();
            if ((texto.length() <= 140)) {
                SharedPreferences settings = v.getContext().getSharedPreferences("Config", 0);
                Rest r = new Rest();
                r.enviarAlerta(latitude, longitude, settings.getString("phone", ""), settings.getString("token", ""), texto, v.getContext(), activity);
                activity.findViewById(R.id.botonMoradoLayout2).setVisibility(View.INVISIBLE);
                activity.findViewById(R.id.botonMoradoLayout3).setVisibility(View.VISIBLE);

                activity.findViewById(R.id.pararAlerta).setOnClickListener(new ManejadorEnviarAlerta(activity, false, true, false));
            } else {
                Toast.makeText(v.getContext(), "¡Introduce una descripción de menos de 140 caracteres!", Toast.LENGTH_SHORT).show();
            }
        }else if(parar){
            SharedPreferences settings = v.getContext().getSharedPreferences("Config", 0);
            Rest r = new Rest();
            r.pararAlerta(settings.getString("phone", ""), settings.getString("token", ""), v.getContext(), activity);
            activity.findViewById(R.id.botonMoradoLayout3).setVisibility(View.INVISIBLE);
            activity.findViewById(R.id.botonMoradoLayout1).setVisibility(View.VISIBLE);
        }else if(!volver){
            activity.findViewById(R.id.botonMoradoLayout1).setVisibility(View.INVISIBLE);
            activity.findViewById(R.id.botonMoradoLayout2).setVisibility(View.VISIBLE);
            activity.findViewById(R.id.botonPedirAyuda).setOnClickListener(new ManejadorEnviarAlerta(activity, true, false, false));
            activity.findViewById(R.id.botonNoPedirAyuda).setOnClickListener(new ManejadorEnviarAlerta(activity, false, false, true));
        }else{
            activity.findViewById(R.id.botonMoradoLayout3).setVisibility(View.INVISIBLE);
            activity.findViewById(R.id.botonMoradoLayout2).setVisibility(View.INVISIBLE);
            activity.findViewById(R.id.botonMoradoLayout1).setVisibility(View.VISIBLE);
        }

    }
}
