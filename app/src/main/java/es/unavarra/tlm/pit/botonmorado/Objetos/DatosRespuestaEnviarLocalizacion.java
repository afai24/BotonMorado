package es.unavarra.tlm.pit.botonmorado.Objetos;

/**
 * Created by ds on 27/11/2017.
 */

public class DatosRespuestaEnviarLocalizacion {

    private String status;
    private String phone;
    private String name;
    private String text;
    private String longitude;
    private String latitude;

    public DatosRespuestaEnviarLocalizacion(String status, String phone, String name, String text, String longitude, String latitude) {
        this.status = status;
        this.phone = phone;
        this.name = name;
        this.text = text;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}
