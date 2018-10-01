package es.unavarra.tlm.pit.botonmorado;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import es.unavarra.tlm.pit.botonmorado.Objetos.DatosRespuestaEnviarLocalizacion;
import es.unavarra.tlm.pit.botonmorado.Objetos.DatosRespuestaServidor;
import es.unavarra.tlm.pit.botonmorado.Objetos.DatosRespuestaVerificacion;

/**
 * Created by ds on 16/11/2017.
 */

public class ResponseHandler extends AsyncHttpResponseHandler {

    private Context context = null;
    private Context mContext;
    private Activity actual;
    private String evento;
    public ResponseHandler(Context context, Activity actual, String evento) {
        this.context = context;
        this.actual = actual;
        this.evento = evento;
        this.mContext =context;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        String response = new String(responseBody);
        switch (evento) {
            case "register":        DatosRespuestaServidor respuestaRegistro = new Gson().fromJson(response, DatosRespuestaServidor.class);
                                    if(respuestaRegistro.getStatus().equals("ok")){
                                        CharSequence texto = "Tu cuenta se ha creado correctamente";
                                        Toast.makeText(context, texto, Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(actual, Verificacion.class);
                                        actual.startActivity(intent);
                                        actual.finish();
                                    }else{
                                        Toast.makeText(this.context, "Error: "+respuestaRegistro.getStatus(), Toast.LENGTH_LONG).show();
                                    }
                                    break;

            case "verify":          DatosRespuestaVerificacion respuestaVerificacion = new Gson().fromJson(response, DatosRespuestaVerificacion.class);
                                    if(respuestaVerificacion.getStatus().equals("ok")){
                                        CharSequence texto = "Tu cuenta se ha verificado correctamente";
                                        Toast.makeText(context, texto, Toast.LENGTH_SHORT).show();

                                        SharedPreferences settings = context.getSharedPreferences("Config", 0);
                                        SharedPreferences.Editor editor = settings.edit();
                                        editor.putString("token", respuestaVerificacion.getToken());
                                        editor.commit();
                                        Intent intent = new Intent(actual, BotonMorado.class);
                                        actual.startActivity(intent);
                                        actual.finish();
                                    }else{
                                        Toast.makeText(this.context, "Error: "+respuestaVerificacion.getStatus(), Toast.LENGTH_LONG).show();
                                    }
                                    break;

            case "alert":           DatosRespuestaServidor respuestaEnviarAlerta = new Gson().fromJson(response, DatosRespuestaServidor.class);
                                    if(respuestaEnviarAlerta.getStatus().equals("ok")) {
                                        Toast.makeText(this.context, "Alerta enviada correctamente", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(this.context, "Ha sucedido un error", Toast.LENGTH_LONG).show();
                                    }
                                    break;

            case "stopAlert":       DatosRespuestaServidor respuestaPararAlerta = new Gson().fromJson(response, DatosRespuestaServidor.class);
                                    if(respuestaPararAlerta.getStatus().equals("ok")) {
                                        Toast.makeText(this.context, "Alerta parada correctamente", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(this.context, "Ha sucedido un error", Toast.LENGTH_LONG).show();
                                    }
                                    break;

            case "checkAlerts":     DatosRespuestaServidor respuestaCheckAlerts = new Gson().fromJson(response, DatosRespuestaServidor.class);
                                    TextView mensaje = (TextView) actual.findViewById(R.id.texto_buscar_alertas);
                                    if(respuestaCheckAlerts.getStatus().equals("alert")){
                                        mensaje.setText("¡Hay alguien a tu alrededor que necesita ayuda! ¿Quieres ayudar?");
                                        actual.findViewById(R.id.si_no_buscar_alertas).setVisibility(View.VISIBLE);
                                    }else if(respuestaCheckAlerts.getStatus().equals("noAlert")){
                                        mensaje.setText("Tranquil@, ahora mismo no hay nadie alrededor que necesite ayuda.");
                                        actual.findViewById(R.id.si_no_buscar_alertas).setVisibility(View.INVISIBLE);
                                    }
                                    actual.findViewById(R.id.botonQuieroAyudar).setVisibility(View.INVISIBLE);
                                    actual.findViewById(R.id.layoutBuscarAlertas1).setVisibility(View.VISIBLE);
                                    break;

            case "sendLocation":    Type mapType = new TypeToken<Map<String, DatosRespuestaEnviarLocalizacion>>() {}.getType();
                                    Map<String, DatosRespuestaEnviarLocalizacion> respuestaEnviarLocalizacion = new Gson().fromJson(response, mapType);
                                    ArrayList<String> alertas = new ArrayList<String>();
                                    final ArrayList<String> latitudes = new ArrayList<String>();
                                    final ArrayList<String> longitudes = new ArrayList<String>();
                                    boolean alerta = false;
                                    for (Map.Entry<String, DatosRespuestaEnviarLocalizacion> entry : respuestaEnviarLocalizacion.entrySet()) {
                                        Log.d("hola",entry.getKey() + "/" + entry.getValue().getStatus());
                                        if(entry.getValue().getStatus().equals("alertFound")){
                                            alerta = true;
                                            latitudes.add(entry.getValue().getLatitude());
                                            longitudes.add(entry.getValue().getLongitude());
                                            SharedPreferences ubicacion = context.getSharedPreferences("ubicacion",0);
                                            double distancia = distancia(Double.parseDouble(ubicacion.getString("latitud", "")), Double.parseDouble(entry.getValue().getLatitude()), Double.parseDouble(ubicacion.getString("longitud", "")), Double.parseDouble(entry.getValue().getLongitude()));
                                            if(entry.getValue().getText().equals("")){
                                                alertas.add("-Nombre: " + entry.getValue().getName() + "\n"
                                                        + "-Problema: Sin describir." + "\n"
                                                        + "-Distancia: "+Math.round(distancia)+" metros."
                                                );
                                            }else {
                                                alertas.add("-Nombre: " + entry.getValue().getName() + "\n"
                                                        + "-Problema: " + entry.getValue().getText() + "\n"
                                                        + "-Distancia: "+Math.round(distancia)+" metros."
                                                );
                                            }
                                        }
                                    }
                                    if(alerta) {
                                        ListView list = (ListView) actual.findViewById(R.id.lista_buscar_alertas);
                                        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(actual, android.R.layout.simple_list_item_1, alertas);
                                        list.setAdapter(adaptador);
                                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                String latitud = latitudes.get(position); //-1
                                                String longitud = longitudes.get(position);

                                                SharedPreferences coordenadasAyuda =  context.getSharedPreferences("coordenadasAyuda",0);
                                                SharedPreferences.Editor editorc = coordenadasAyuda.edit();
                                                editorc.putString("latitudA", latitud );
                                                editorc.putString("longitudA", longitud);
                                                editorc.commit();

                                                Intent intent = new Intent(actual, MapsActivity.class);
                                                actual.startActivity(intent);


                                            }
                                        });
                                        actual.findViewById(R.id.layoutBuscarAlertas1).setVisibility(View.INVISIBLE);
                                        actual.findViewById(R.id.layoutBuscarAlertas2).setVisibility(View.INVISIBLE);
                                        actual.findViewById(R.id.layoutBuscarAlertas3).setVisibility(View.VISIBLE);

                                    }else{
                                        actual.findViewById(R.id.layoutBuscarAlertas1).setVisibility(View.INVISIBLE);
                                        actual.findViewById(R.id.layoutBuscarAlertas3).setVisibility(View.INVISIBLE);
                                        actual.findViewById(R.id.layoutBuscarAlertas2).setVisibility(View.VISIBLE);
                                    }
                                    break;
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Log.d("ResponseHandler", "Request returned error " + statusCode);
    }

    public static double distancia(double lat1, double lat2, double lon1, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        return distance;
    }
}
