package es.unavarra.tlm.pit.botonmorado;

import android.app.Activity;
import android.content.Context;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;
import es.unavarra.tlm.pit.botonmorado.Objetos.DatosCheckAlertas;
import es.unavarra.tlm.pit.botonmorado.Objetos.DatosComprobarRegistro;
import es.unavarra.tlm.pit.botonmorado.Objetos.DatosComprobarVerificacion;
import es.unavarra.tlm.pit.botonmorado.Objetos.DatosEnviarAlerta;
import es.unavarra.tlm.pit.botonmorado.Objetos.DatosEnviarLocalizacion;
import es.unavarra.tlm.pit.botonmorado.Objetos.DatosPararAlerta;

/**
 * Created by ds on 21/11/2017.
 */

public class Rest {

    public Rest() {
        super();
    }

    public void enviarRegistro(String textoNombre, String textoTelefono, Context context, Activity actual){
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("content-type", "application/json; charset=UTF-8");
        DatosComprobarRegistro datos = new DatosComprobarRegistro(textoNombre, textoTelefono, "register",FirebaseInstanceId.getInstance().getToken());
        try {
            client.post(context, "https://botonmorado.ml/api/", new StringEntity(new Gson().toJson(datos)), "aplication/json", new ResponseHandler(context, actual, "register"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void enviarVerificacion(String textoTelefono, String textoCodigo, Context context, Activity actual){
        AsyncHttpClient client = new AsyncHttpClient();
        DatosComprobarVerificacion datos = new DatosComprobarVerificacion(textoTelefono, textoCodigo, "verify");
        try {
            client.post(context, "https://botonmorado.ml/api/", new StringEntity(new Gson().toJson(datos)), "aplication/json", new ResponseHandler(context, actual, "verify"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void enviarAlerta(String latitude, String longitude, String phone, String token, String text, Context context, Activity actual){
        AsyncHttpClient client = new AsyncHttpClient();
        DatosEnviarAlerta datos = new DatosEnviarAlerta(latitude, longitude, phone, token, text, "alert");
        try {
            client.post(context, "https://botonmorado.ml/api/", new StringEntity(new Gson().toJson(datos)), "aplication/json", new ResponseHandler(context, actual, "alert"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void pararAlerta(String phone, String token, Context context, Activity actual){
        AsyncHttpClient client = new AsyncHttpClient();
        DatosPararAlerta datos = new DatosPararAlerta(phone, token, "stopAlert");
        try {
            client.post(context, "https://botonmorado.ml/api/", new StringEntity(new Gson().toJson(datos)), "aplication/json", new ResponseHandler(context, actual, "stopAlert"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void checkAlertas(String phone, String token, Context context, Activity actual){
        AsyncHttpClient client = new AsyncHttpClient();
        DatosCheckAlertas datos = new DatosCheckAlertas(phone, token, "checkAlerts");
        try {
            client.post(context, "https://botonmorado.ml/api/", new StringEntity(new Gson().toJson(datos)), "aplication/json", new ResponseHandler(context, actual, "checkAlerts"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void enviarLocalizacion(String phone, String token, String latitude, String longitude, Context context, Activity actual){
        AsyncHttpClient client = new AsyncHttpClient();
        DatosEnviarLocalizacion datos = new DatosEnviarLocalizacion(phone, token, latitude, longitude, "sendLocation");
        try {
            client.post(context, "https://botonmorado.ml/api/", new StringEntity(new Gson().toJson(datos)), "aplication/json", new ResponseHandler(context, actual, "sendLocation"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void enviarLocalizacionSinRespuesta(String phone, String token, String latitude, String longitude, Context context, Activity actual){
        AsyncHttpClient client = new AsyncHttpClient();
        DatosEnviarLocalizacion datos = new DatosEnviarLocalizacion(phone, token, latitude, longitude, "sendLocation");
        try {
            client.post(context, "https://botonmorado.ml/api/", new StringEntity(new Gson().toJson(datos)), "aplication/json", new ResponseHandler(context, actual, "locationNoResponse"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
