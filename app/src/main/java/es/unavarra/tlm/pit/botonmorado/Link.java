package es.unavarra.tlm.pit.botonmorado;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

/**
 * Created by diego on 08/12/17.
 */

class Link implements View.OnClickListener {
    private final Activity context;
    private final String url;


    public Link (Activity context,String url){
        this.context=context;
        this.url=url;
    }
    @Override
    public void onClick(View view) {
        this.goToUrl(this.url);
    }
    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        this.context.startActivity(launchBrowser);
    }
}
