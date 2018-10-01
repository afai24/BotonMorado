package es.unavarra.tlm.pit.botonmorado.Objetos;

/**
 * Created by ds on 16/11/2017.
 */

public class DatosComprobarRegistro {

    private String name;
    private String phone;
    private String event;
    private String token;

    public DatosComprobarRegistro(String name, String phone, String event,String token) {
        this.name = name;
        this.phone = phone;
        this.event = event;
        this.token=token;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEvent() {
        return event;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
