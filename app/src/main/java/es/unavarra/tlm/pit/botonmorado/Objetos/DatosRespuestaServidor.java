package es.unavarra.tlm.pit.botonmorado.Objetos;

/**
 * Created by ds on 16/11/2017.
 */

public class DatosRespuestaServidor {

    private String status;

    public DatosRespuestaServidor(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
