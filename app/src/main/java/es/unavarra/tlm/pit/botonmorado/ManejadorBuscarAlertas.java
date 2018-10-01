package es.unavarra.tlm.pit.botonmorado;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;

/**
 * Created by ds on 24/11/2017.
 */

public class ManejadorBuscarAlertas implements View.OnClickListener {

    private Activity activity;
    private boolean ayudar;

    public ManejadorBuscarAlertas(Activity activity, boolean ayudar) {
        this.activity = activity;
        this.ayudar = ayudar;
    }

    @Override
    public void onClick(View v) {

        if(!ayudar) {
            SharedPreferences settings = v.getContext().getSharedPreferences("Config", 0);
            Rest r = new Rest();
            r.checkAlertas(settings.getString("phone", ""), settings.getString("token", ""), v.getContext(), activity);

            activity.findViewById(R.id.boton_buscar_alertas_si).setOnClickListener(new ManejadorBuscarAlertas(activity, true));
            activity.findViewById(R.id.boton_buscar_alertas_no).setOnClickListener(new ManejadorOnClick(activity, BotonMorado.class));
        }else{
            SharedPreferences settings = v.getContext().getSharedPreferences("Config", 0);
            Rest r = new Rest();

            SharedPreferences ubicacion = v.getContext().getSharedPreferences("ubicacion",0);
            String latitude = ubicacion.getString("latitud", "");
            String longitude = ubicacion.getString("longitud", "");

            r.enviarLocalizacion(settings.getString("phone", ""), settings.getString("token", ""), latitude, longitude, v.getContext(), activity);
        }

    }
}
