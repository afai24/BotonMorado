package es.unavarra.tlm.pit.botonmorado;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

/**
 * Created by ds on 23/11/2017.
 */

public class ManejadorOnClick implements View.OnClickListener {

    Activity actual;
    Class destino;

    public ManejadorOnClick(Activity actual, Class destino){
        this.actual = actual;
        this.destino = destino;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(actual, destino);
        actual.startActivity(intent);
        actual.finish();
    }
}
