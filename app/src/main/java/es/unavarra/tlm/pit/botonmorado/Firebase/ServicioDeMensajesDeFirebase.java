package es.unavarra.tlm.pit.botonmorado.Firebase;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import es.unavarra.tlm.pit.botonmorado.R;

/**
 * Created by ai on 28/11/2017.
 */

public class ServicioDeMensajesDeFirebase extends FirebaseMessagingService
{


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {

        if (remoteMessage.getNotification() != null)
        {

            String titulo = remoteMessage.getNotification().getTitle();
            String texto = remoteMessage.getNotification().getBody();


            //Opcional: mostramos la notificación en la barra de estado
            showNotification(titulo, texto);
        }
        //SOLO FUNCIONA con la app en primer plano.
        //Para cuando notificaciones de datos, implementar enviar al servidor la ubicación,
        //Utilizaremos este tipo de notificaciones ya que nos permite mayor maniobrabilidad
        //El problema o la app esta en primer plano o si esta en segundo plano el usuario
        // tiene que darle click a la notificacion, asi pilla el intent (con esos datos extras que
        //se envian).

    }


    //Para mostrar un aviso si la app se encuentra en primer plano
    private void showNotification(String title, String text)
    {

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_ic_notification)
                        .setContentTitle(title)
                        .setContentText(text);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
