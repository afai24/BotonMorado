package es.unavarra.tlm.pit.botonmorado.Objetos;

/**
 * Created by ds on 23/11/2017.
 */

public class DatosRespuestaVerificacion {

    private String status;
    private String token;

    public DatosRespuestaVerificacion(String status, String token) {
        this.status = status;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
