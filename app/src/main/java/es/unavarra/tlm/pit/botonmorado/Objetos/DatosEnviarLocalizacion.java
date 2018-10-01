package es.unavarra.tlm.pit.botonmorado.Objetos;

/**
 * Created by ds on 27/11/2017.
 */

public class DatosEnviarLocalizacion {

    private String phone;
    private String token;
    private String latitude;
    private String longitude;
    private String event;

    public DatosEnviarLocalizacion(String phone, String token, String latitude, String longitude, String event) {
        this.phone = phone;
        this.token = token;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

}
