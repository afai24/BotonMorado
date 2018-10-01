package es.unavarra.tlm.pit.botonmorado.Objetos;

/**
 * Created by ds on 21/11/2017.
 */

public class DatosComprobarVerificacion {

    private String phone;
    private String code;
    private String event;

    public DatosComprobarVerificacion(String phone, String code, String event) {
        this.phone = phone;
        this.code = code;
        this.event = event;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
