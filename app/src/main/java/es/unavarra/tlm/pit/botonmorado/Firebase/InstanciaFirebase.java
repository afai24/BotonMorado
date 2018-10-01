package es.unavarra.tlm.pit.botonmorado.Firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ai on 28/11/2017.
 */

public class InstanciaFirebase extends FirebaseInstanceIdService {



    @Override
    public void onTokenRefresh() {
        //Instancia del token que se genera tras la instalación
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();


        /*  Crear este metodo en el caso de que quisieramos mandarlo al servidor,
        con asynhttp?
        sendRegistrationToServer(refreshedToken);*/
    }
}