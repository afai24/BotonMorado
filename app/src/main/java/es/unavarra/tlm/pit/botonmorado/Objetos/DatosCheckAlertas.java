package es.unavarra.tlm.pit.botonmorado.Objetos;

/**
 * Created by ds on 25/11/2017.
 */

public class DatosCheckAlertas {

    private String phone;
    private String token;
    private String event;

    public DatosCheckAlertas(String phone, String token, String event) {
        this.phone = phone;
        this.token = token;
        this.event = event;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

}
