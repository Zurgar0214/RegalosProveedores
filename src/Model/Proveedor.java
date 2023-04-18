package Model;

import org.json.JSONObject;
public class Proveedor {
    private String nombreProveedor;
    private Double precioEnvio;
    public Proveedor(String nombreProveedor, Double precioEnvio) {
        this.nombreProveedor = nombreProveedor;
        this.precioEnvio = precioEnvio;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public Double getPrecioEnvio() {
        return precioEnvio;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "nombreProveedor='" + nombreProveedor + '\'' +
                ", precioEnvio=" + precioEnvio +
                '}';
    }
}
