package es.unavarra.tlm.pit.botonmorado.Objetos;

/**
 * Created by ds on 23/11/2017.
 */

public class DatosEnviarAlerta {

    private String latitude;
    private String longitude;
    private String phone;
    private String token;
    private String text;
    private String event;

    public DatosEnviarAlerta(String latitude, String longitude, String phone, String token, String text, String event) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.token = token;
        this.text = text;
        this.event = event;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

}
